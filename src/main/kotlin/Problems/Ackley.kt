package Problems

import Problem
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.sqrt

class Ackley (numberDimension: Int, maxFEs: Int): Problem("Problems.Ackley", numberDimension, maxFEs) {

    init {
        val lowerLimitValue = -32.768
        val upperLimitValue = 32.768

        lowerLimit.fill(lowerLimitValue)
        upperLimit.fill(upperLimitValue)
    }
    override fun evaluate(x: DoubleArray): Double {
        currentFes++
        assert(currentFes <= maxFEs)

        //Recommended variable values
        val a = 20.0
        val b = 0.2
        val c = 2 * PI

        val sum1 = x.sumByDouble { it * it } // vsoto kvadrato
        val sum2 = x.sumByDouble { cos(c * it) } //vsoto kosinusov

        val term1 = -a * exp(-b * sqrt(sum1 / numberDimension))
        val term2 = -exp(sum2 / numberDimension)

        return term1 + term2 + a + exp(1.0)
    }
}
