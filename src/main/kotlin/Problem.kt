abstract class Problem (
    val name: String, //name of problem
    val numberDimension: Int,
    var maxFEs: Int, //maksimalno število ovrednotenj (kolikokrat se lahko pokliče ovrednotenje funkcije)
) {
    val lowerLimit: DoubleArray = DoubleArray(numberDimension) // spodnja meja
    val upperLimit: DoubleArray = DoubleArray(numberDimension) // zgornja meja
    var currentFes: Int = 0

    abstract fun evaluate(x: DoubleArray): Double

    fun randomSolutionGenerator(): Solution { //naključno generiranje kromosomov/kandidatov
        val x = DoubleArray(numberDimension)

        for (i in 0 until numberDimension)
        {
            x[i] = lowerLimit[i] + (upperLimit[i] - lowerLimit[i]) * Math.random()
        }
        return Solution(x, evaluate(x))
    }


}