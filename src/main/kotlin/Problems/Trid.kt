package Problems

import Problem

class Trid(numberDimension: Int, maxFEs: Int) : Problem("Problems.Trid", numberDimension, maxFEs) {

    init {
        val lowerLimitValue = DoubleArray(numberDimension) { -numberDimension.toDouble() * numberDimension.toDouble() }
        val upperLimitValue = DoubleArray(numberDimension) { numberDimension.toDouble() * numberDimension.toDouble() }

        lowerLimit = lowerLimitValue
        upperLimit = upperLimitValue
    }

    override fun evaluate(x: DoubleArray): Double {
        currentFes++
        assert(currentFes <= maxFEs)

        val n = x.size
        var term1 = 0.0
        var term2 = 0.0

        for (i in 0 until n) {
            term1 += (x[i] - 1)
            term2 += (x[i] - 1) * (x[i] - 1)
        }

        return term1 * term1 - term2
    }
}
