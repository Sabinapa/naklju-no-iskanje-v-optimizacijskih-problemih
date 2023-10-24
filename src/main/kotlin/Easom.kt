import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.pow

class Easom(numberDimension: Int, maxFEs: Int) : Problem("Easom", numberDimension, maxFEs) {

    init {
        val lowerLimitValue = -100.0
        val upperLimitValue = 100.0

        lowerLimit.fill(lowerLimitValue)
        upperLimit.fill(upperLimitValue)
    }

    override fun evaluate(x: DoubleArray): Double {
        currentFes++
        assert(currentFes <= maxFEs)

        val x1 = x[0]
        val x2 = x[1]

        val term1 = -cos(x1) * cos(x2)
        val term2 = -exp(-(x1 - Math.PI).pow(2) - (x2 - Math.PI).pow(2))

        return term1 * exp(term2)
    }
}
