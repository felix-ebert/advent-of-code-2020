package days

class Day5 : Day(5) {

    override fun partOne(): Any {
        val seats = inputList.map { getSeatId(it) }.toMutableList()
        return seats.maxOrNull()!!
    }

    override fun partTwo(): Any {
        val seats = inputList.map { getSeatId(it) }.toMutableList().sorted()

        for (i in 1 until seats.size) {
            val expectedSeat: Int = seats[i - 1] + 1
            if (seats[i] != expectedSeat) {
                return expectedSeat
            }
        }
        return 0
    }

    private fun getSeatId(seat: String): Int {
        var rows = List(128) { i -> i }
        var columns = List(8) { i -> i }
        seat.asSequence().forEach {
            when (it) {
                'F' -> rows = rows.subList(0, rows.size / 2)
                'B' -> rows = rows.subList(rows.size / 2, rows.size)
                'L' -> columns = columns.subList(0, columns.size / 2)
                'R' -> columns = columns.subList(columns.size / 2, columns.size)
            }
        }
        return rows[0] * 8 + columns[0]
    }
}