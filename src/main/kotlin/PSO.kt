import kotlin.math.abs
import kotlin.random.Random

class PSO(private val problem: Problem) : Algorithm<ParticleSolution?>()
{
    private val swarmSize = 20
    private val inertiaWeight = 0.7 //w omega - intertia weight
    private val c1 = 2.0 //cognitive coefficient kognitivni koeficient (določa vpliva najboljše znane pozicije delca na posodobitev hitrosti)
    private val c2 = 2.0 // social coefficient

    private var globalBestSolution: ParticleSolution? = null
    private val particles: MutableList<ParticleSolution> = mutableListOf()

    override fun run(): ParticleSolution?
    {
        problem.currentFes = 0

        // Step 1: Define the number of particles S
        val w = inertiaWeight
        val S = swarmSize
        //println(w)

        // Step 2: Initialize particles
        for (i in 0 until problem.numberDimension) {
            // Step 2: Initialize the particle's position x_i ~ U(b_lo, b_up) and velocity v_i
            val particle = problem.randomSolutionGenerator()
            val initialX = particle.x
            // Evaluate the solution $x_i$ denoting as $f(p_i)$
            particle.fitness = problem.evaluate(initialX)
            problem.currentFes++

            val bestPosition = initialX.copyOf()

            val initialVelocity = DoubleArray(problem.numberDimension)
            {
                Random.nextDouble(-abs(problem.upperLimit[i] - problem.lowerLimit[i]), abs(problem.upperLimit[i] - problem.lowerLimit[i]))
            }

            // Initialize the particle's best position to the current position: p_i <- x_i
            particles.add(ParticleSolution(initialX, particle.fitness, initialVelocity, bestPosition))

            if (globalBestSolution == null || particles[i].fitness < globalBestSolution!!.fitness) {
                updateGlobalBest(particles[i])
            }

            //println("Dimenzija " + i)
            //println(globalBestSolution!!.fitness)

        }

        // Terminacijski kriteriji ki določajo pogoje kdaj algoritem ustavi
        repeat(problem.maxFEs - problem.numberDimension)
        {
            // For each particle x_i
            for (i in 0 until particles.size)
            {
                for (dim in 0 until problem.numberDimension) {
                    //naključne vrednosti v območju [0, 1)
                    val rand1 = Random.nextDouble()
                    val rand2 = Random.nextDouble()
                    val pi = particles[i].bestPosition[dim] // najboljša poznana pozicija delca
                    val xi = particles[i].x[dim] //trenutno poznana pozicija delca
                    val g = globalBestSolution!!.x[dim] // globalno najboljše znane pozicije
                    // intercijska teža w ki stabilizira gibanje delcev


                    val cognitiveComponent = c1 * rand1 * (pi - xi)
                    val socialComponent = c2 * rand2 * (g - xi)

                    // Update particle's velocity
                    particles[i].velocity[dim] = w * particles[i].velocity[dim] + cognitiveComponent + socialComponent

                    // update particles position xi = xi + vi
                    particles[i].x[dim] = particles[i].x[dim] + particles[i].velocity[dim]

                    if (!problem.isFeasible(particles[i])) {
                        problem.setFeasible(particles[i])
                    }

                    // Update particle's best position if the condition is met
                    val newFitness = problem.evaluate(particles[i].x)    // Evaluate the new solution
                    problem.currentFes++

                    //println("newFitness: $newFitness")
                    //println("particles[i].fitness: ${particles[i].fitness}")

                    if (newFitness < particles[i].fitness) {
                        particles[i].fitness = newFitness
                        if (particles[i].fitness < globalBestSolution!!.fitness) {

                            updateGlobalBest(particles[i])
                        }

                    }

                }
            }
            bestSolution = globalBestSolution
            //println("Global Best Fitness: ${globalBestSolution!!.fitness}")

        }
        return globalBestSolution
    }

    private fun updateGlobalBest(solution: ParticleSolution) {
            globalBestSolution = ParticleSolution(
                    solution.x.copyOf(),
                    solution.fitness,
                    solution.velocity.copyOf(),
                    solution.bestPosition.copyOf()
            )

    }
}