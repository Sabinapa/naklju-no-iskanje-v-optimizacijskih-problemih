fun runProblem(problem: Problem, numRuns: Int): Triple<Solution, Double, Double> {
    //val dimensions = problem.numberDimension
    val hillClimbing = HillClimbing(problem, stepSize = 0.1)
    val statistics = Statistics(hillClimbing, numRuns)

    val (minFitness, avgFitness, stdDev) = statistics.runStatistics(problem)

    println("${problem.name} min: $minFitness avg: $avgFitness std: $stdDev")
    return Triple(minFitness, avgFitness, stdDev)
}

fun main() {
    val dimensions = 2
    val maxFEs = 10000

    // Define problem names using an enum or constants
    val problemNames = arrayOf("Sphere", "Ackley", "Schwefel26", "Rosenbrock", "Bukin", "CarromTable", "Easom", "Trid")

    println("Naloga 2: naključno iskanje v optimizacijskih problemih\n")

    for (problemType in 1..problemNames.size) {
        val problemName = problemNames[problemType - 1]
        println(problemName)

        val problem = when (problemType) {
            1 -> Sphere(dimensions, maxFEs)
            2 -> Ackley(dimensions, maxFEs)
            3 -> Schwefel26(dimensions, maxFEs)
            4 -> Rosenbrock(dimensions, maxFEs)
            5 -> Bukin(maxFEs)
            6 -> CarromTable(dimensions, maxFEs)
            7 -> Easom(dimensions, maxFEs)
            8 -> Trid(dimensions, maxFEs)
            else -> throw IllegalArgumentException("Invalid problem type: $problemType")
        }

        runProblem(problem, maxFEs)
        println()
    }
}