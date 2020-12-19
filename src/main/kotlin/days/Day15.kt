package days

class Day15 : Day(15) {

    override fun partOne(): Any {
        val n = inputString.split(',').map { it.toInt() }.toMutableList()

        for (i in n.lastIndex until 2019) {
            if (n.singleOrNull { it == n[i] } != null) n.add(0)
            else {
                val t = n.toIntArray().copyOfRange(0, i)
                n.add(i - t.lastIndexOf(n[i]))
            }
        }

        return n[n.lastIndex]
    }

    override fun partTwo(): Any {
        val n = inputString.split(',').mapIndexed { index, s -> s.toInt() to index }.toMap().toMutableMap()
        var number = 0

        for (i in n.size until 29999999) {
            val last = n.put(number, i)
            number = if (last == null) 0 else i - last
        }

        return number
    }
}