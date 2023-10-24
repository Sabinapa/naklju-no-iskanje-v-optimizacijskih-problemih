class Sphere(numberDimension: Int, maxFEs: Int): Problem("Sphere", numberDimension, maxFEs) {
    init {
        val lowerLimitValue = -100.0
        val upperLimitValue = 100.0

        lowerLimit.fill(lowerLimitValue)
        upperLimit.fill(upperLimitValue)
    }

    override fun evaluate(x: DoubleArray): Double {
        currentFes++
        assert(currentFes <= maxFEs)

        var fitness = 0.0
        for (i in 0 until numberDimension) {
            fitness += x[i] * x[i]
        }

        return fitness
    }
}