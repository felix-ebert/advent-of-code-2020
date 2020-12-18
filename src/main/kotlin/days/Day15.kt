package days

class Day15 : Day(15) {

    override fun partOne(): Any {
        val n = inputString.split(',').map { it.toInt() }.toMutableList()

        for (i in n.lastIndex until 30000000 - 1) {
            if (n.singleOrNull { it == n[i] } != null) n.add(0)
            else {
                val t = n.toIntArray().copyOfRange(0, i)
                n.add(i - t.lastIndexOf(n[i]))
            }
        }

        return n[n.lastIndex]
    }

    override fun partTwo(): Any {
        return 0
    }
}