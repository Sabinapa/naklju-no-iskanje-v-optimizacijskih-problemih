class ParticleSolution(x: DoubleArray, fitness: Double, var velocity: DoubleArray,var bestPosition: DoubleArray) : Solution(x, fitness)
{
    override fun toString(): String {
        return "ParticleSolution(x=${x.joinToString(", ") { String.format("%.4f", it) }}, " +
                "fitness=${String.format("%.4f", fitness)}, " +
                "velocity=${velocity.joinToString(", ") { String.format("%.4f", it) }})"
    }

}