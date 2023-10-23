class Rosenbrock (numberDimension: Int, maxFEs: Int): Problem("Rosenbrock", numberDimension, maxFEs) {

    init {
        val lowerLimitValue = -5.0
        val upperLimitValue = 10.0

        lowerLimit.fill(lowerLimitValue)
        upperLimit.fill(upperLimitValue)
    }

    override fun evaluate(x: DoubleArray): Double {
        currentFes++
        assert(currentFes <= maxFEs)

        var fitness = 0.0
        for (i in 0 until numberDimension - 1) {
            val xi = x[i]
            val xiPlus1 = x[i + 1]

            val term1 = 100 * (xiPlus1 - xi * xi) * (xiPlus1 - xi * xi)
            val term2 = (1 - xi) * (1 - xi)
            fitness += term1 + term2
        }

        return fitness
    }

}