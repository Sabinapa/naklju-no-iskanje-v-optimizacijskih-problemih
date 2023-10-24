import kotlin.math.*

class CarromTable(numberDimension: Int, maxFEs: Int) : Problem("CarromTable", numberDimension, maxFEs) {
    init {
        val lowerLimitValue = -10.0
        val upperLimitValue = 10.0

        lowerLimit.fill(lowerLimitValue)
        upperLimit.fill(upperLimitValue)
    }

    override fun evaluate(x: DoubleArray): Double {
        currentFes++
        assert(currentFes <= maxFEs)

        val x1 = x[0]
        val x2 = x[1]

        val term1 = 1 - (sqrt(x1 * x1 + x2 * x2) / Math.PI)
        val term2 = exp(2.0 * abs(term1))
        val term3 = cos(x1) * cos(x1)
        val term4 = cos(x2) * cos(x2)

        return -(1.0 / 30.0) * term2 * term3 * term4
    }
}
