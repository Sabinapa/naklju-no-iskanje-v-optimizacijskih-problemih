
class Solution(var x: DoubleArray, var fitness: Double) {
    override fun toString(): String {
        return "Solution(x=${x.joinToString(", ")  { "%.2f".format(it) }}, fitness=${String.format("%.4f", fitness)})"
    }
}