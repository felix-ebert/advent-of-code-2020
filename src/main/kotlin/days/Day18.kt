package days

class Day18 : Day(18) {

    override fun partOne(): Any {
        return inputList.map { it.replace(" ", "") }.sumOf { evaluateRuleOne(it.iterator()) }
    }

    override fun partTwo(): Any {
        return 0
    }

    private fun evaluateRuleOne(expression: CharIterator): Long {
        // source: https://todd.ginsberg.com/post/advent-of-code/2020/day18/
        // own try was based on substrings + recursion
        // keep this solution because of the simplicity and understandability
        val numbers = mutableListOf<Long>()
        var operator = Char.MIN_VALUE // empty char is not allowed in Kotlin
        while (expression.hasNext()) {
            when (val next = expression.nextChar()) {
                '(' -> numbers += evaluateRuleOne(expression)
                ')' -> break
                in setOf('+', '*') -> operator = next
                else -> numbers += next.toString().toLong()
            }
            if (numbers.size == 2) {
                val a = numbers.removeLast()
                val b = numbers.removeLast()
                numbers += if (operator == '+') a + b else a * b
            }
        }
        return numbers.first()
    }
}