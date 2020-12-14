package days

import kotlin.math.absoluteValue

class Day12 : Day(12) {

    override fun partOne(): Any {
        val instructions = inputList.map { it.take(1) to it.substring(1).toInt() }.toMutableList()
        return simulateInstructions(instructions, false)
    }

    override fun partTwo(): Any {
        val instructions = inputList.map { it.take(1) to it.substring(1).toInt() }.toMutableList()
        return simulateInstructions(instructions, true)
    }

    private fun simulateInstructions(instructions: List<Pair<String, Int>>, moveWaypoint: Boolean): Int {
        var ship = Pair(0, 0)
        var waypoint = Pair(10, 1)
        var direction = 90

        for (ins in instructions) {
            when (ins.first) {
                "N", "S", "E", "W" -> {
                    if (moveWaypoint) waypoint = updatePosition(waypoint, ins)
                    else ship = updatePosition(ship, ins) // move ship
                }
                "L" -> {
                    if (moveWaypoint) {
                        when (ins.second) {
                            90 -> waypoint = Pair(-1 * waypoint.second, waypoint.first)
                            180 -> waypoint = Pair(-1 * waypoint.first, -1 * waypoint.second)
                            270 -> waypoint = Pair(waypoint.second, -1 * waypoint.first)
                        }
                        waypoint = updatePosition(waypoint, ins)
                    } else direction = (direction - ins.second) % 360
                }
                "R" -> {
                    if (moveWaypoint) {
                        when (ins.second) {
                            90 -> waypoint = Pair(waypoint.second, -1 * waypoint.first)
                            180 -> waypoint = Pair(-1 * waypoint.first, -1 * waypoint.second)
                            270 -> waypoint = Pair(-1 * waypoint.second, waypoint.first)
                        }
                    } else direction = (direction + ins.second) % 360
                }
                "F" -> {
                    ship = if (moveWaypoint) Pair(
                        ship.first + waypoint.first * ins.second,
                        ship.second + waypoint.second * ins.second
                    )
                    else updatePosition(ship, Pair(getDirection(direction), ins.second))
                }
            }
        }

        return ship.first.absoluteValue + ship.second.absoluteValue
    }

    private fun updatePosition(position: Pair<Int, Int>, ins: Pair<String, Int>): Pair<Int, Int> {
        var x = position.first
        var y = position.second
        when (ins.first) {
            "N" -> y += ins.second
            "S" -> y -= ins.second
            "E" -> x += ins.second
            "W" -> x -= ins.second
        }
        return Pair(x, y)
    }

    private fun getDirection(degrees: Int): String {
        return when (if (degrees > 0) degrees else degrees + 360) {
            0 -> "N"
            90 -> "E"
            180 -> "S"
            270 -> "W"
            else -> "N"
        }
    }
}