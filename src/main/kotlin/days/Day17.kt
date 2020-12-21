package days

class Day17 : Day(17) {
    // adapted from: https://todd.ginsberg.com/post/advent-of-code/2020/day17/

    override fun partOne(): Any {
        var grid = parseInput { x, y -> Cube3D(x, y, 0) }
        repeat(6) {
            grid = grid.simulate()
        }
        return grid.count { it.value }
    }

    override fun partTwo(): Any {
        var grid = parseInput { x, y -> Cube4D(x, y, 0, 0) }
        repeat(6) {
            grid = grid.simulate()
        }
        return grid.count { it.value }
    }

    interface Cube {
        fun findNeighbours(): List<Cube>
    }

    data class Cube3D(val x: Int, val y: Int, val z: Int) : Cube {
        override fun findNeighbours(): List<Cube> {
            return (x - 1..x + 1).flatMap { dx ->
                (y - 1..y + 1).flatMap { dy ->
                    (z - 1..z + 1).mapNotNull { dz ->
                        Cube3D(dx, dy, dz).takeUnless { it == this }
                    }
                }
            }
        }
    }

    data class Cube4D(val x: Int, val y: Int, val z: Int, val w: Int) : Cube {
        override fun findNeighbours(): List<Cube> {
            return (x - 1..x + 1).flatMap { dx ->
                (y - 1..y + 1).flatMap { dy ->
                    (z - 1..z + 1).flatMap { dz ->
                        (w - 1..w + 1).mapNotNull { dw ->
                            Cube4D(dx, dy, dz, dw).takeUnless { it == this }
                        }
                    }
                }
            }
        }
    }

    private fun parseInput(cubeFunction: (Int, Int) -> Cube): Map<Cube, Boolean> {
        return inputList.flatMapIndexed { y, row ->
            row.mapIndexed { x, char ->
                cubeFunction(x, y) to (char == '#')
            }
        }.toMap()
    }

    private fun Map<Cube, Boolean>.simulate(): Map<Cube, Boolean> {
        val nextMap = this.toMutableMap()
        keys.forEach { cube ->
            cube.findNeighbours().forEach { neighbor ->
                nextMap.putIfAbsent(neighbor, false) // add only if no such mapping exists
            }
        }
        nextMap.entries.forEach { (cube, active) ->
            val activeNeighbours = cube.findNeighbours().count { this.getOrDefault(it, false) }
            nextMap[cube] = when {
                active && activeNeighbours in 2..3 -> true
                !active && activeNeighbours == 3 -> true
                else -> false
            }
        }
        return nextMap
    }
}