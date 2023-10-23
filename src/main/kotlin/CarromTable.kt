import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

class CarromTable(numberDimension: Int, maxFEs: Int) : Problem("CarromTable", numberDimension, maxFEs) {

    init {
        require(numberDimension == 2) { "CarromTable only works with 2 dimensions" }
        val lowerLimitValue = doubleArrayOf(-10.0, -10.0)
        val upperLimitValue = doubleArrayOf(10.0, 10.0)

        lowerLimit = lowerLimitValue
        upperLimit = upperLimitValue
    }

    override fun evaluate(x: DoubleArray): Double {
        currentFes++
        assert(currentFes <= maxFEs)

        val x1 = x[0]
        val x2 = x[1]

        val term1 = 100 - (x1 * x1 + x2 * x2)
        val term2 = 0.5 * (cos(sin(abs(x1 * x1 - x2 * x2))) - 0.5)

        return term1 - term2
    }
}
