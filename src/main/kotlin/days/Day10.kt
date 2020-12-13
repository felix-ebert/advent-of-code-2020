package days

class Day10 : Day(10) {

    override fun partOne(): Any {
        val adapters = inputList.map { it.toInt() }.sorted().toMutableSet()

        // add device's built-in adapter
        adapters.add(adapters.maxOrNull()!! + 3)

        return getAdapterChain(adapters)
    }

    override fun partTwo(): Any {
        val adapters = inputList
            .map { it.toInt() }
            .toMutableSet()
            .apply {
                add(0)
                add(maxOrNull()!! + 3) // add device's built-in adapter
            }
            .sorted()

        val arrangements = LongArray(adapters.last() + 1)
        arrangements[0] = 1

        for (adapter in adapters) {
            for (diff in 1..3) {
                val next = adapter + diff
                if (adapters.contains(next))
                    arrangements[next] += arrangements[adapter]
            }
        }

        return arrangements[adapters.last()]
    }

    private fun getAdapterChain(adapters: Set<Int>): Int {
        var currentJolt = 0
        var oneJoltDif = 0
        var threeJoltDif = 0

        for (adapter in adapters) {
            when (adapter - currentJolt) {
                1 -> oneJoltDif++
                3 -> threeJoltDif++
                else -> return 0 // no chain found that uses all adapters
            }
            currentJolt = adapter
        }

        return oneJoltDif * threeJoltDif
    }
}