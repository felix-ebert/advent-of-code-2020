package days

class Day1 : Day(1) {

    override fun partOne(): Any {
        val input = inputList.map { it.toInt() }
        for (i in input) {
            for (j in input) {
                if (i + j == 2020) return i * j
            }
        }
        return "no pair matched"
    }

    override fun partTwo(): Any {
        val input = inputList.map { it.toInt() }
        for (i in input) {
            for (j in input) {
                for (k in input) {
                    if (i + j + k == 2020) return i * j * k
                }
            }
        }
        return "no pair matched"
    }
}
