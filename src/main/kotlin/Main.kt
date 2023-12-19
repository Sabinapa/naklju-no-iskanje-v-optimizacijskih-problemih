import Problems.*
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

fun main() {

    println("Naloga 3: ALGORITEM PSO\n")

    val dimensions = 30
    val maxFEs = 100000
    val numRuns = 50

    /*
    val problems = listOf(
            //Sphere(dimensions, maxFEs),
            //Bukin(maxFEs),
            //CarromTable(dimensions, maxFEs),
            //Easom(dimensions, maxFEs),
            //Ackley(dimensions, maxFEs),
            Schwefel26(dimensions, maxFEs),
            //Rosenbrock(dimensions, maxFEs),
            //Trid(dimensions, maxFEs)
    )

     */

    /*
    for (problem in problems) {
        val outputFile = File("PSO-Pauric_${problem.name}D${problem.numberDimension}.txt")
        BufferedWriter(FileWriter(outputFile)).use { writer ->
            for (run in 1..numRuns)
            {
                val algorithm = PSO(problem)
                val result = algorithm.run()
                //val statistics = Statistics(algorithm)
                //val (bestSolution, averageFitness, standardDeviation) = statistics.runStatistics(problem)

                result?.let {
                    val bestFitness = it.fitness
                    val fitnessString = "$run: $bestFitness"
                    println(fitnessString)
                    writer.write(fitnessString + "\n")
                }
           }
        }
    }

     */


   // for (problem in problems) {
        val problem = Rosenbrock(dimensions, maxFEs)
        println("\n\nRunning PSO for ${problem.name}...")
        val algorithm = PSO(problem)

        //val result = algorithm.run()
        val statistics = Statistics(algorithm)

        val (bestSolution, averageFitness, standardDeviation) = statistics.runStatistics(problem)
        println("Best Solution: ${bestSolution?.fitness}")
        println("Average Fitness: $averageFitness")
        println("Standard Deviation: $standardDeviation")

   // }

}