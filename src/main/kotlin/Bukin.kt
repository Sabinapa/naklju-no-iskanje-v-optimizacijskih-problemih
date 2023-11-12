import kotlin.math.abs
import kotlin.math.sqrt

class Bukin(maxFEs: Int) : Problem("Bukin", 2, maxFEs) {

    init {
        val lowerLimitValue = doubleArrayOf(-15.0, -3.0)
        val upperLimitValue = doubleArrayOf(-5.0, 3.0)

        lowerLimit = lowerLimitValue
        upperLimit = upperLimitValue

    }
    override fun evaluate(x: DoubleArray): Double {
        currentFes++
        assert(currentFes <= maxFEs)

        val x1 = x[0]
        val x2 = x[1]

        val term1 = 100.0 * sqrt(abs(x2 - 0.01 * x1 * x1))
        val term2 = 0.01 * abs(x1 + 10.0)

        return term1 + term2
    }
}