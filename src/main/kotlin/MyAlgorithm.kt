class MyAlgorithm(problem: Problem) : Algorithm(problem) {

    override fun run(maxIterations: Int): Solution? {
        // Implement your algorithm logic here
        // You can use the 'problem' property inherited from Algorithm

        var iterations = 0

        while (iterations < maxIterations) {
            val solution = problem.randomSolutionGenerator()
            val fitness = problem.evaluate(solution.x)
            iterations++

            updateBestSolution(solution,fitness, iterations)
        }

        printResult()

        return bestSolution
    }
}