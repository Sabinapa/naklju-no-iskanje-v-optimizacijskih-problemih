class Statistics<T: ParticleSolution?>(private val algorithm: Algorithm<T>, private val numRuns: Int = 50) {

    fun runStatistics(problem: Problem): Triple<T, Double, Double> {
        var bestSolution: T? = null
        var sumFitness = 0.0
        var sumSquaredFitness = 0.0
        var runCount = 0

        repeat(numRuns) {
            val result = algorithm.run()
            val fitness = result?.fitness ?: Double.MAX_VALUE

            // Update best solution
            if (bestSolution == null || fitness < bestSolution!!.fitness) {
                bestSolution = result
            }
            println(bestSolution?.fitness)

            // Update sums for calculating average and standard deviation
            sumFitness += fitness
            sumSquaredFitness += fitness * fitness
            runCount++

        }

        val averageFitness = sumFitness / numRuns
        val standardDeviation = calculateStandardDeviation(sumFitness, sumSquaredFitness, numRuns)

        // Print the run count
        //println("Run $runCount: Best Fitness - ${bestSolution?.fitness}")

        // najboljsa resitev med vsemi ponovitvami, povprecna veednost, standardni odklon
        return Triple(bestSolution!!, averageFitness, standardDeviation)
    }

    //standardni odklon na podlagi vsote prilagojenosti, vsote kvadrtov prilagojenosti in število ponovitev
    private fun calculateStandardDeviation(sumFitness: Double, sumSquaredFitness: Double, numRuns: Int): Double {
        val variance = (sumSquaredFitness - sumFitness * sumFitness / numRuns) / (numRuns - 1)
        return kotlin.math.sqrt(variance)
    }

}