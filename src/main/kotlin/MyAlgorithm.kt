class MyAlgorithm(problem: Problem) : Algorithm(problem) {

    override fun run(maxIterations: Int): Solution? {
        var iterations = 0

        while (iterations < maxIterations) {
            val solution = problem.randomSolutionGenerator()
            val fitness = problem.evaluate(solution.x)

            updateBestSolution(solution,fitness)
        }

        printResult()

        return bestSolution
    }
}