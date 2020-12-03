package days

class Day3 : Day(3) {

    override fun partOne(): Any {
        return runSlope(3, 1)
    }

    override fun partTwo(): Any {
        return runSlope(1, 1) *
                runSlope(3, 1) *
                runSlope(5, 1) *
                runSlope(7, 1) *
                runSlope(1, 2)
    }

    private fun runSlope(right: Int, down: Int): Int {
        val width = inputList[0].length
        val height = inputList.size
        val map = Array(width) { arrayOfNulls<Char>(height) }

        inputList.forEachIndexed { y, line ->
            line.asSequence().forEachIndexed { x, character -> map[x][y] = character }
        }

        var x = 0
        var trees = 0

        for (y in 0 until height step down) {
            if (x > width - 1) x -= width
            if (map[x][y] == '#') trees++
            x += right
        }

        return trees
    }
}