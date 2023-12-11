abstract class Problem (
    val name: String, //name of problem
    val numberDimension: Int,
    var maxFEs: Int, //maksimalno število ovrednotenj (kolikokrat se lahko pokliče ovrednotenje funkcije)
) {
    var lowerLimit: DoubleArray = DoubleArray(numberDimension) // spodnja meja
    var upperLimit: DoubleArray = DoubleArray(numberDimension) // zgornja meja
    var currentFes: Int = 0

    abstract fun evaluate(x: DoubleArray): Double

    fun evaluate(solution: Solution) {
        solution.fitness = evaluate(solution.x)
    }

    fun randomSolutionGenerator(): Solution { //naključno generiranje kromosomov/kandidatov
        val x = DoubleArray(numberDimension)

        for (i in 0 until numberDimension)
        {
            x[i] = lowerLimit[i] + (upperLimit[i] - lowerLimit[i]) * Math.random()
        }
        return Solution(x, evaluate(x))
    }

    // preveri če solution v mejah zgornje in spodnje meje
    fun isFeasible(solution: Solution): Boolean
    {
        for (i in 0 until numberDimension) {
            if (solution.x[i] < lowerLimit[i] || solution.x[i] > upperLimit[i]) {
                return false
            }
        }
        return true
    }

    // če ni v mejah zgornje in spodnje meje, ga nastavi na mejo
    // če je pod spodnjo mejo nastavi na spodnjo mejo
    // če je nad zgornjo mejo nastavi na zgornjo mejo
    fun setFeasible(solution: Solution)
    {
        for (i in 0 until numberDimension)
        {
            if (solution.x[i] < lowerLimit[i])
            {
                solution.x[i] = lowerLimit[i]
            }
            else if (solution.x[i] > upperLimit[i])
            {
                solution.x[i] = upperLimit[i]
            }
        }
    }

}