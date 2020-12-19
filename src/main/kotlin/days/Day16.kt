package days

class Day16 : Day(16) {

    private val fields = mutableMapOf<String, List<IntRange>>()
    private val ticket = mutableListOf<Int>()
    private val nearbyTickets = mutableListOf<Int>()

    init {
        var isTicket = false
        var isNearbyTicket = false

        for (line in inputList) {
            when {
                line.startsWith("your ticket") -> isTicket = true
                line.startsWith("nearby tickets") -> isNearbyTicket = true
                line.isEmpty() -> isTicket = false
                isTicket -> line.split(',').forEach { ticket.add(it.toInt()) }
                isNearbyTicket -> line.split(',').forEach { nearbyTickets.add(it.toInt()) }
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
        return nearbyTickets.sumOf { if (validValues.contains(it)) 0 else it }
    }

    override fun partTwo(): Any {
        return 0
    }
}