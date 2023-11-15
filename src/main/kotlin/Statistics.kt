class Statistics(private val algorithm: Algorithm, private val numRuns: Int = 100) {

    fun runStatistics(problem: Problem): Triple<Solution, Double, Double> {
        var bestSolution: Solution? = null
        var sumFitness = 0.0
        var sumSquaredFitness = 0.0

        repeat(numRuns) {
            val result = algorithm.run(maxIterations = 10000)
            val fitness = result?.fitness ?: Double.MAX_VALUE

            // Update best solution
            if (bestSolution == null || fitness < bestSolution!!.fitness) {
                bestSolution = result
            }

            // Update sums for calculating average and standard deviation
            sumFitness += fitness
            sumSquaredFitness += fitness * fitness
        }

        val averageFitness = sumFitness / numRuns
        val standardDeviation = calculateStandardDeviation(sumFitness, sumSquaredFitness, numRuns)

        // najboljsa resitev med vsemi ponovitvami, povprecna veednost, standardni odklon
        return Triple(bestSolution!!, averageFitness, standardDeviation)
    }

    //standardni odklon na podlagi vsote prilagojenosti, vsote kvadrtov prilagojenosti in število ponovitev
    private fun calculateStandardDeviation(sumFitness: Double, sumSquaredFitness: Double, numRuns: Int): Double {
        val variance = (sumSquaredFitness - sumFitness * sumFitness / numRuns) / (numRuns - 1)
        return kotlin.math.sqrt(variance)
    }

}