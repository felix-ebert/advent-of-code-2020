package days

class Day7 : Day(7) {

    override fun partOne(): Any {
        val bags = inputList.map { parseRule(it) }.toMap()
        return bags.count { containsBag(it.value.keys.toList(), bags) }
    }

    override fun partTwo(): Any {
        val bags = inputList.map { parseRule(it) }.toMap()
        return countBags(bags["shiny gold"], bags)
    }

    private fun parseRule(input: String): Pair<String, Map<String, Int>> {
        val color = input.substringBefore(" bags")
        var bags = emptyMap<String, Int>()

        if (input.substringAfter("contain ") != "no other bags.") {
            bags = input.substringAfter("contain ").split(", ").map {
                it.substringAfter(" ").substringBefore(" bag") to it.substringBefore(" ").toInt()
            }.toMap()
        }
        return Pair(color, bags)
    }

    private fun containsBag(values: List<String>?, bags: Map<String, Map<String, Int>>): Boolean {
        val bag = "shiny gold"
        if (values.isNullOrEmpty()) return false
        return values.contains(bag) || values.any { containsBag(bags[it]!!.keys.toList(), bags) }
    }

    private fun countBags(values: Map<String, Int>?, bags: Map<String, Map<String, Int>>): Int {
        return if (values.isNullOrEmpty()) {
            0
        } else {
            values.map { it.value + it.value * countBags(bags[it.key], bags) }.sum()
        }
    }
}