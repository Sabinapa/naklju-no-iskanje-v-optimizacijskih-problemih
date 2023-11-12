//V razredu naj bo tudi parameter stepSize, ki nam pove velikost koraka. Parameter stepSize naj ima neko privzeto vrednost, lahko pa ga nastavimo preko konstruktorja.

class HillClimbing (problem: Problem, private val stepSize: Double = 0.1) : Algorithm(problem) {

    override fun run(maxIterations: Int): Solution? {
        var iterations = 0

        while (iterations < maxIterations) {
            val currentSolution = problem.randomSolutionGenerator()
            val currentFitness = problem.evaluate(currentSolution.x)

            val newSolution = perturbSolution(currentSolution)
            val newFitness = problem.evaluate(newSolution.x)

            iterations++

            if (newFitness < currentFitness) {
                updateBestSolution(newSolution, newFitness, iterations)
            }
        }

        printResult()

        return bestSolution
    }

    private fun perturbSolution(solution: Solution): Solution {
        val newSolution = Solution(solution.x.copyOf(), solution.fitness)

        for (i in 0 until problem.numberDimension) {
            newSolution.x[i] += stepSize * (2 * Math.random() - 1)
        }

        return newSolution
    }


}
