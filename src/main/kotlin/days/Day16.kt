package days

class Day16 : Day(16) {

    private val fields = mutableMapOf<String, List<IntRange>>()
    private val myTicket = mutableListOf<Int>()
    private val nearbyTickets = mutableListOf<List<Int>>()

    init {
        var isTicket = false
        var isNearbyTicket = false

        for (line in inputList) {
            when {
                line.startsWith("your ticket") -> isTicket = true
                line.startsWith("nearby tickets") -> isNearbyTicket = true
                line.isEmpty() -> isTicket = false
                isTicket -> line.split(',').forEach { myTicket.add(it.toInt()) }
                isNearbyTicket -> nearbyTickets.add(line.split(',').map { it.toInt() })
                else -> {
                    val field = line.substringBefore(':')
                    val ranges: List<IntRange> = line.substringAfter(": ").split(" or ").map {
                        IntRange(it.substringBefore('-').toInt(), it.substringAfter('-').toInt())
                    }
                    fields[field] = ranges
                }
            }
        }
    }

    override fun partOne(): Any {
        val validValues = fields.flatMap { it.value.flatMap { range -> range.toList() } }
        return nearbyTickets.flatten().sumOf { if (validValues.contains(it)) 0 else it }
    }

    override fun partTwo(): Any {
        // filter out the valid tickets
        val validValues = fields.flatMap { it.value.flatMap { range -> range.toList() } }
        val validTickets = nearbyTickets.filter { it.all { t -> validValues.contains(t) } }.toMutableList()
        validTickets.add(myTicket)

        // find all numbers for every column
        val numbers = mutableMapOf<Int, MutableList<Int>>()
        for (i in myTicket.indices) numbers[i] = mutableListOf()

        for (ticket in validTickets) {
            for (i in ticket.indices) {
                numbers[i]?.add(ticket[i])
            }
        }

        // find all fields matching for every column
        val possibleFields = mutableMapOf<Int, MutableList<String>>()
        for (i in myTicket.indices) possibleFields[i] = mutableListOf()

        for ((col, values) in numbers) {
            for ((field, ranges) in fields) {
                if (values.all { it in ranges[0] || it in ranges[1] }) {
                    possibleFields[col]?.add(field)
                }
            }
        }

        // reduce that list that there are no fields with multiple solutions
        val foundFields = mutableMapOf<Int, String>()
        while (foundFields.size < myTicket.size) {
            for ((col, fields) in possibleFields) {
                if (fields.size == 1) {
                    val field = fields.first()
                    foundFields[col] = field
                    possibleFields.values.forEach { it.remove(field) }
                }
            }
        }

        // return the product of the “departure” fields on my ticket
        return foundFields.filter { it.value.startsWith("departure") }.keys.fold(1L) { acc, i -> acc * myTicket[i] }
    }
}