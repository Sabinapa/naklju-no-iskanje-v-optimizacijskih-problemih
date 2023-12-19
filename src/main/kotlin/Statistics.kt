import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

class Statistics<T : ParticleSolution?>(private val algorithm: Algorithm<T>, private val numRuns: Int = 50) {

    fun runStatistics(problem: Problem): Triple<T, Double, Double> {
        var bestSolution: T? = null
        var sumFitness = 0.0
        var sumSquaredFitness = 0.0
        var runCount = 0
        var standardDeviation = 0.0  // Deklaracija tukaj
        var averageFitness = 0.0

        // Odpremo datoteko enkrat pred začetkom ponovitev
        val fileName = File("PSO-Pauric_${problem.name}D${problem.numberDimension}.txt")
        BufferedWriter(FileWriter(fileName)).use { writer ->

            repeat(numRuns) {
                val result = algorithm.run()
                val fitness = result?.fitness ?: Double.MAX_VALUE

                // Update best solution
                if (bestSolution == null || fitness < bestSolution!!.fitness) {
                    bestSolution = result
                }
                //println("Run ${runCount + 1}: Best Fitness - ${bestSolution?.fitness}")

                // Shranimo vrednost najboljše prilagojenosti v datoteko
                val fitnessString = "${bestSolution?.fitness}"
                writer.write(fitnessString + "\n")

                // Update sums for calculating average and standard deviation
                sumFitness += fitness
                sumSquaredFitness += fitness * fitness
                runCount++
            }

            // Po končanih ponovitvah shranimo povprečno vrednost in standardni odklon
            averageFitness = sumFitness / numRuns
            standardDeviation = calculateStandardDeviation(sumFitness, sumSquaredFitness, numRuns)

            //writer.write("\nAverage Fitness: $averageFitness\n")
            //writer.write("Standard Deviation: $standardDeviation\n")

        }

        // najboljša rešitev med vsemi ponovitvami, povprečna vrednost, standardni odklon
        return Triple(bestSolution!!, averageFitness, standardDeviation)
    }

    private fun calculateStandardDeviation(sumFitness: Double, sumSquaredFitness: Double, numRuns: Int): Double {
        val variance = (sumSquaredFitness - sumFitness * sumFitness / numRuns) / (numRuns - 1)
        return kotlin.math.sqrt(variance)
    }

}
