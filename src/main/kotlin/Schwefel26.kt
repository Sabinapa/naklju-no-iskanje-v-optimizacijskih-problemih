import kotlin.math.abs
import kotlin.math.sin
import kotlin.math.sqrt

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

        var sum = 0.0
        for (i in 0 until numberDimension) {
            sum += x[i] * sin(sqrt(abs(x[i])))
        }

        return 418.9829 * numberDimension - sum
    }
}