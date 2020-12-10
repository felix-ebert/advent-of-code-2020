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
        var instructions = inputList.map { it.substringBefore(" ") to it.substringAfter(" ").toInt() }.toTypedArray()
        val instructionsCopy = instructions.copyOf()

        var accumulator = 0
        instructions.forEachIndexed { index, ins ->
            run {
                instructions[index] = Pair(swap(ins.first), ins.second)
                val result = runProgram(instructions)
                if (result.first) accumulator = result.second
                else instructions = instructionsCopy.copyOf()
            }
        }

        return accumulator
    }

    private fun swap(instruction: String): String {
        return when (instruction) {
            "jmp" -> "nop"
            "nop" -> "jmp"
            else -> instruction
        }
    }

    private fun runProgram(instructions: Array<Pair<String, Int>>): Pair<Boolean, Int> {
        val visited = arrayOfNulls<Pair<String, Int>>(instructions.size)
        var isRunning = true
        var ptr = 0
        var accumulator = 0

        while (isRunning) {
            if (ptr == instructions.size) isRunning = false
            else {
                if (visited[ptr] == null) visited[ptr] = Pair(instructions[ptr].first, 1)
                else {
                    if (visited[ptr]!!.second > 1) return Pair(false, 0) // infite loop detected
                    else visited[ptr] = Pair(visited[ptr]!!.first, visited[ptr]!!.second + 1)
                }

                when (instructions[ptr].first) {
                    "acc" -> accumulator += instructions[ptr++].second
                    "jmp" -> ptr += instructions[ptr].second
                    else -> ptr++ //nop
                }
            }
        }
        return Pair(true, accumulator)
    }
}