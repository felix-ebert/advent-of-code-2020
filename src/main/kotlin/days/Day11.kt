package days

import kotlin.math.max
import kotlin.math.min

class Day11 : Day(11) {

    override fun partOne(): Any {
        return simulateSeatModel(parseSeatModel(), ::findNeighbours, 4)
    }

    override fun partTwo(): Any {
        return simulateSeatModel(parseSeatModel(), ::findFirstVisibleSeats, 5)
    }

    private fun parseSeatModel(): Array<Array<Char>> {
        val seats = Array(inputList.first().length) { Array(inputList.size) { ' ' } }
        inputList.forEachIndexed { x, row ->
            row.forEachIndexed { y, c ->
                seats[x][y] = c
            }
        }
        return seats
    }

    private fun simulateSeatModel(seats: Array<Array<Char>>, seatChecker: (seats: Array<Array<Char>>, row: Int, col: Int) -> List<Char>, occupiedSeats: Int): Int {
        // deep copy old model
        val newModel = Array(inputList.first().length) { Array(inputList.size) { ' ' } }
        seats.forEachIndexed { x, row ->
            row.forEachIndexed { y, c ->
                newModel[x][y] = c
            }
        }

        // apply rules
        seats.forEachIndexed { x, row ->
            row.forEachIndexed { y, c ->
                when (c) {
                    'L' -> if (!seatChecker(seats, x, y).contains('#')) newModel[x][y] = '#'
                    '#' -> if (seatChecker(seats, x, y).count { it == '#' } >= occupiedSeats) newModel[x][y] = 'L'
                }
            }
        }

        return if (seats.contentDeepEquals(newModel)) seats.sumOf { it.count { c -> c == '#' } }
        else simulateSeatModel(newModel, seatChecker, occupiedSeats)
    }

    private fun findNeighbours(seats: Array<Array<Char>>, row: Int, col: Int): List<Char> {
        // adapted from: https://stackoverflow.com/questions/652106/finding-neighbours-in-a-two-dimensional-array/19074651#19074651
        val neighbourSeats = mutableListOf<Char>()
        for (x in max(0, row - 1)..min(row + 1, seats.size - 1)) {
            for (y in max(0, col - 1)..min(col + 1, seats[0].size - 1)) {
                if (row != x || col != y) {
                    neighbourSeats.add(seats[x][y])
                }
            }
        }
        return neighbourSeats
    }

    private fun findFirstVisibleSeats(seats: Array<Array<Char>>, row: Int, col: Int): List<Char> {
        // adapted from: https://www.geeksforgeeks.org/search-a-word-in-a-2d-grid-of-characters/
        val firstVisibleSeats = mutableListOf<Char>()

        val x = intArrayOf(-1, -1, -1, 0, 0, 1, 1, 1)
        val y = intArrayOf(-1, 0, 1, -1, 1, -1, 0, 1)

        for (dir in 0 until 8) {
            var rd: Int = row + x[dir]
            var cd: Int = col + y[dir]

            for (i in 0..max(seats.size, seats[0].size)) {
                if (rd >= seats.size || rd < 0 || cd >= seats[0].size || cd < 0)
                    break

                if (seats[rd][cd] != '.') {
                    firstVisibleSeats.add(seats[rd][cd])
                    break
                }

                rd += x[dir]
                cd += y[dir]

            }
        }

        return firstVisibleSeats
    }
}