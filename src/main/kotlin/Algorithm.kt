
abstract class Algorithm(val problem: Problem) { //abstract
    var bestSolution: Solution? = null

    abstract fun run(maxIterations: Int) : Solution?
    /*
    { //abstract prejme problem + vrne najbojso resitev
        var iterations = 0

        while (iterations < maxIterations) {
            //problem.currentFes < problem.maxFEs && iterations < maxIterations
            val solution = problem.randomSolutionGenerator()
            val fitness = problem.evaluate(solution.x)
            iterations++

            updateBestSolution(solution, fitness, iterations)
        }

        printResult()
        //return bestSolution!!
    }
     */


    fun updateBestSolution(solution: Solution, fitness: Double, iterations: Int) {
        if (bestSolution == null || fitness < bestSolution!!.fitness) {
            bestSolution = Solution(solution.x.copyOf(), fitness)
            println("$iterations. ${bestSolution.toString()}")
        }
    }

    fun printResult() {
        println("Result = ${bestSolution?.toString() ?: "No solution found"}")
    }
}
