
abstract class Algorithm(val problem: Problem) { //abstract
    var bestSolution: Solution? = null

    abstract fun run(maxIterations: Int) : Solution?


    fun updateBestSolution(solution: Solution, fitness: Double) {
        if (bestSolution == null || fitness < bestSolution!!.fitness) {
            bestSolution = Solution(solution.x.copyOf(), fitness)
            //println("$iterations. ${bestSolution.toString()}")
        }
    }

    fun printResult() {
        println("Result = ${bestSolution?.toString() ?: "No solution found"}")
    }
}
