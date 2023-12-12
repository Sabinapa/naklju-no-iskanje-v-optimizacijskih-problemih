
open class Algorithm<T> {
    var bestSolution: Solution? = null

    open fun run() : T = Unit as T

    //abstract fun run1(maxIterations: Int) : U

    /*
    fun updateBestSolution(solution: Solution, fitness: Double) {
        if (bestSolution == null || fitness < bestSolution!!.fitness) {
            bestSolution = Solution(solution.x.copyOf(), fitness)
        }
    }

     */


    fun printResult() {
        println("Result = ${bestSolution?.toString() ?: "No solution found"}")
    }

}
