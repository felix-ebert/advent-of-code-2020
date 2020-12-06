package days


class Day6 : Day(6) {

    override fun partOne(): Any {
        return inputString.split("\n\n").sumBy { it.asSequence().distinct().count { c -> c.isLetter() } }
    }

    override fun partTwo(): Any {
        val alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray()
        return inputString.split("\n\n").map {
            it.split("\n").fold(alphabet) { start, c -> start.intersect(c.toCharArray().asList()).toCharArray() }.size
        }.sum()
    }
}