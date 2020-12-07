package days

class Day7 : Day(7) {

    override fun partOne(): Any {
        val bags = inputList.map { parseRule(it) }.toMap()
        return bags.count { containsBag(it.value, bags) }
    }

    override fun partTwo(): Any {
        val bags = inputList.map { parseRuleTwo(it) }.toMap()
        var sum = 0
//        for (b in bags.values) {
//            sum += containsBagTwo(b, bags)
//        }
        return sum
    }

    private fun parseRule(input: String): Pair<String, List<String>> {
        val color = input.substringBefore(" bags")
        var bags = emptyList<String>()

        if (input.substringAfter("contain ") != "no other bags.") {
            bags = input.substringAfter("contain ").split(", ").map {
                it.substringAfter(" ").substringBefore(" bag")
            }
        }
        return Pair(color, bags)
    }

    private fun containsBag(values: List<String>?, bags: Map<String, List<String>>): Boolean {
        val bag = "shiny gold"
        if (values.isNullOrEmpty()) return false
        return values.contains(bag) || values.any { containsBag(bags[it], bags) }
    }

    private fun parseRuleTwo(input: String): Pair<String, Map<String, Int>> {
        val color = input.substringBefore(" bags")
        var bags = emptyMap<String, Int>()

        if (input.substringAfter("contain ") != "no other bags.") {
            bags = input.substringAfter("contain ").split(", ").map {
                it.substringAfter(" ").substringBefore(" bag") to it.substringBefore(" ").toInt()
            }.toMap()
        }
        return Pair(color, bags)
    }

    private fun containsBagTwo(values: Map<String,Map<String, Int>>?, bags: Map<String, Map<String, Int>>): Int {
        val bag = "shiny gold"
        if (values.isNullOrEmpty()) return 0
        // if (values.contains(bag)) println("key: $key values: $values is true")

        // TODO
//        if (values.contains(bag)) {
//            return values.count()
//        } else {
//            return values.values.sumOf { containsBagTwo(bags[it], bags) }
//        }
        return 0
    }
}