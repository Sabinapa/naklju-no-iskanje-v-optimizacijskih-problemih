class Algorithm(private val problem: Problem) {
    private var bestSolution: Solution? = null

    fun run(maxIterations: Int) {
        var iterations = 0

        while (shouldContinueOptimization(iterations, maxIterations)) {
            val solution = problem.randomSolutionGenerator()
            val fitness = problem.evaluate(solution.x)
            iterations++

            updateBestSolution(solution, fitness, iterations)
        }

        printResult()
    }

    private fun shouldContinueOptimization(iterations: Int, maxIterations: Int): Boolean {
        return problem.currentFes < problem.maxFEs && iterations < maxIterations
    }

    private fun updateBestSolution(solution: Solution, fitness: Double, iterations: Int) {
        if (bestSolution == null || fitness < bestSolution!!.fitness) {
            bestSolution = Solution(solution.x.copyOf(), fitness)
            println("$iterations. ${bestSolution.toString()}")
        }
    }

    private fun printResult() {
        println("Rezultat = ${bestSolution.toString()}")
    }
}
