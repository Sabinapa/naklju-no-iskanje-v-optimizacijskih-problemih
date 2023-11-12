class HillClimbing (problem: Problem, private val stepSize: Double = 0.1) : Algorithm(problem) {

    override fun run(maxIterations: Int): Solution? {
        var iterations = 0

        while (iterations < maxIterations) {
            val currentSolution = problem.randomSolutionGenerator()
            val currentFitness = problem.evaluate(currentSolution.x)

            val newSolution = perturbSolution(currentSolution)
            val newFitness = problem.evaluate(newSolution.x)

            iterations++

            if (newFitness < currentFitness) {
                updateBestSolution(newSolution, newFitness, iterations)
            }
        }

        printResult()

        return bestSolution
    }

    //Obvezni del (zahteva 2n sosedov): Za vsako dimenzijo naredite dva koraka, enega navzgor (+) in enega navzdol (-), s čimer boste ustvarili 2n sosedov.

    private fun perturbSolution(solution: Solution): Solution {
        val newSolution = Solution(solution.x.copyOf(), solution.fitness)

        for (i in 0 until problem.numberDimension) {

            //naredimo dva koraka, enega navzgor (+) in enega navzdol (-)
            newSolution.x[i] += stepSize
            val fitnessPlus = problem.evaluate(newSolution.x)

            newSolution.x[i] -= 2 * stepSize
            val fitnessMinus = problem.evaluate(newSolution.x)

            //če je fitnessPlus boljši kot fitnessMinus, potem je newSolution.x[i] = newSolution.x[i] + stepSize
            if (fitnessPlus < fitnessMinus) {
                newSolution.x[i] += 2 * stepSize
            }
            //če je fitnessMinus boljši kot fitnessPlus, potem je newSolution.x[i] = newSolution.x[i] - stepSize
            else {
                newSolution.x[i] -= stepSize
            }

        }

        return newSolution
    }


}
