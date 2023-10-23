class Schwefel26(numberDimension: Int, maxFEs: Int): Problem("Schwefel26", numberDimension, maxFEs)
{
    init {
        val lowerLimitValue = -500.0
        val upperLimitValue = 500.0

        lowerLimit.fill(lowerLimitValue)
        upperLimit.fill(upperLimitValue)
    }

    override fun evaluate(x: DoubleArray): Double {
        currentFes++
        assert(currentFes <= maxFEs)

        val sum = x.sumByDouble { Math.abs(it) }

        return 420.968746 * numberDimension - sum
    }
}