class HillClimbing (problem: Problem, private val stepSize: Double = 0.1, private val targetFitness: Double = 0.001) : Algorithm(problem) {

    override fun run(maxIterations: Int): Solution? {
        var iterations = 0
        var proximityToOptimum = Double.MAX_VALUE
        problem.currentFes = 0

        while (iterations < maxIterations && proximityToOptimum > targetFitness)
        {
            //generira nakljucne zacetne resitve
            val zacetniPosameznik = problem.randomSolutionGenerator()
            var currentFitness = problem.evaluate(zacetniPosameznik.x)

            // Preverjanje, ali je naključno generirana rešitev veljavna
            if (!problem.isFeasible(zacetniPosameznik)) {
                problem.setFeasible(zacetniPosameznik)
            }

            val newSolution = Generiranjesosedov(zacetniPosameznik)
            //oceni nova rešitev
            val newFitness = problem.evaluate(newSolution.x)

            iterations++

            //preveri veljavnost nove resitve
            if (!problem.isFeasible(newSolution)) {
                problem.setFeasible(newSolution)
            }

            //če fitnes nove rešitve boljsi od fitnesa trenutne se podobi
            if (newFitness < currentFitness) {
                updateBestSolution(newSolution, newFitness, iterations)
            }

            // Izračunaj novo bližino do optimuma
            proximityToOptimum = kotlin.math.abs(bestSolution?.fitness ?: Double.MAX_VALUE - targetFitness)
        }

        //printResult()

        return bestSolution
    }

    //Obvezni del (zahteva 2n sosedov): Za vsako dimenzijo naredite dva koraka, enega navzgor (+) in enega navzdol (-), s čimer boste ustvarili 2n sosedov.

    private fun Generiranjesosedov(solution: Solution): Solution {
        //val newSolution = Solution(solution.x.copyOf(), solution.fitness)
        val neighbors = mutableListOf<Solution>()

        for (i in 0 until problem.numberDimension) {

            // sosed premika v pozitivni smeri
            val positiveNeighbor = Solution(solution.x.copyOf(), solution.fitness)
            positiveNeighbor.x[i] += stepSize
            neighbors.add(positiveNeighbor)

            // sosed premika v negativno smer po korakih
            val negativeNeighbor = Solution(solution.x.copyOf(), solution.fitness)
            negativeNeighbor.x[i] -= stepSize
            neighbors.add(negativeNeighbor)

        }

        // Evaluate fitness for all neighbors
        val fitnessValues = neighbors.map { neighbor -> problem.evaluate(neighbor.x) }

        // najdi soseda z najboljsim fitnesom
        val bestNeighborIndex = fitnessValues.indexOf(fitnessValues.minOrNull())
        return neighbors[bestNeighborIndex]

    }
}
