fun runProblem(problem: Problem, maxFEs: Int) {
    val solution = problem.randomSolutionGenerator()
    val optimum = problem.evaluate(DoubleArray(problem.numberDimension) { 0.0 })

    println("Optimal value: $optimum")
    println("Solution: $solution")

    val algorithm = Algorithm(problem)
    algorithm.run(maxFEs)
}

fun main(args: Array<String>) {

    /*
     val ackley = Ackley(dimensions, maxFEs) // 2 dimenziji, maksimalno 3000 ovrednotenj
            val solution = ackley.randomSolutionGenerator()
            val optimum = ackley.evaluate(DoubleArray(dimensions) { 0.0 })
            println(optimum)
            println(solution)

            val algorithm = Algorithm(ackley)
            algorithm.run(maxFEs) //  maxFes = 3000
     */

    if (args.size < 4) {
        println("Usage: java rndSearch <problemType> <dimension> <maxFEs>")
        return
    }

    val problemType = args[1].toInt()
    val dimensions = args[2].toInt()
    val maxFEs = args[3].toInt()

    println("Naloga 1: naključno iskanje v optimizacijskih problemih\n")

    when (problemType) {
        1 -> {
            println("Sphere")
            val sphere = Sphere(dimensions, maxFEs)
            runProblem(sphere, maxFEs)
        }
        2 -> {
            println("Ackley")
            val ackley = Ackley(dimensions, maxFEs)
            runProblem(ackley, maxFEs)
        }
        3 -> {
            println("Schwefel26")
            val schwefel26 = Schwefel26(dimensions, maxFEs)
            runProblem(schwefel26, maxFEs)
        }
        4 -> {
            println("Rosenbrock")
            val rosenbrock = Rosenbrock(dimensions, maxFEs)
            runProblem(rosenbrock, maxFEs)
        }
        5 -> {
            println("Bukin")
            val bukin = Bukin(dimensions, maxFEs)
            runProblem(bukin, maxFEs)
        }
        6 -> {
            println("CarromTable")
            val carromTable = CarromTable(dimensions, maxFEs)
            runProblem(carromTable, maxFEs)
        }
        7 -> {
            println("Easom")
            val easom = Easom(dimensions, maxFEs)
            runProblem(easom, maxFEs)
        }
        8 -> {
            println("Trid")
            val trid = Trid(dimensions, maxFEs)
            runProblem(trid, maxFEs)
        }
    }

}