class HillClimbing (problem: Problem, private val stepSize: Double = 0.1, private val targetFitness: Double = 0.001) : Algorithm(problem) {

    override fun run(maxIterations: Int): Solution? {
        //var iterations = 0
        var proximityToOptimum = Double.MAX_VALUE
        problem.currentFes = 0

        //generira nakljucne zacetne resitve
        val begginer = problem.randomSolutionGenerator() //zacetni posameznik
        problem.evaluate(begginer)
        problem.currentFes++



        while (problem.currentFes < maxIterations && proximityToOptimum > targetFitness)
        {

            // Preverjanje, ali je naključno generirana rešitev veljavna
            if (!problem.isFeasible(begginer)) {
                problem.setFeasible(begginer)
            }

            val newSolution = generateNeighbors(begginer)
            problem.currentFes+=problem.numberDimension
            //oceni nova rešitev

            //preveri veljavnost nove resitve
            if (!problem.isFeasible(newSolution)) {
                problem.setFeasible(newSolution)
            }

            //če fitnes nove rešitve boljsi od fitnesa trenutne se podobi
            if (newSolution.fitness < begginer.fitness) {
                //updateBestSolution(newSolution.fitness)
            }

            // Izračunaj novo bližino do optimuma
            proximityToOptimum = kotlin.math.abs(bestSolution?.fitness ?: Double.MAX_VALUE - targetFitness)
        }

        //printResult()

        return bestSolution
    }

    //Obvezni del (zahteva 2n sosedov): Za vsako dimenzijo naredite dva koraka, enega navzgor (+) in enega navzdol (-), s čimer boste ustvarili 2n sosedov.

    private fun generateNeighbors(solution: Solution): Solution {
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
