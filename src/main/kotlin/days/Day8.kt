package days

class Day8 : Day(8) {

    override fun partOne(): Int {
        val instructions = inputList.map { it.substringBefore(" ") to it.substringAfter(" ").toInt() }.toTypedArray()
        val visited = mutableSetOf<Int>()
        var isRunning = true
        var ptr = 0
        var accumulator = 0

        while (isRunning) {
            if (visited.contains(ptr)) isRunning = false
            else {
                visited.add(ptr)
                when (instructions[ptr].first) {
                    "acc" -> accumulator += instructions[ptr++].second
                    "jmp" -> ptr += instructions[ptr].second
                    else -> ptr++ //nop
                }
            }
        }

        return accumulator
    }

    override fun partTwo(): Any {
        return 0
    }
}