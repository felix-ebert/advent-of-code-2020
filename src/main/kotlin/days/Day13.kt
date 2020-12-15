package days

class Day13 : Day(13) {

    override fun partOne(): Any {
        val currentTime = inputList[0].toInt()
        var nextBus = 0
        var nextDeparture = Int.MAX_VALUE

        inputList[1].split(',').forEach {
            if (it != "x") {
                var departure = 0
                while (departure < currentTime) {
                    departure += it.toInt()
                }
                if (departure < nextDeparture) {
                    nextBus = it.toInt()
                    nextDeparture = departure
                }
            }
        }

        return (nextDeparture - currentTime) * nextBus
    }

    override fun partTwo(): Any {
        // through inspiration on Reddit, the chinese remainder theorem was used to avoid brute forcing
        // https://en.wikipedia.org/wiki/Chinese_remainder_theorem
        val busses = mutableListOf<Long>()
        val offsets = mutableListOf<Long>()

        inputList[1].split(',').forEachIndexed { index, bus ->
            if (bus != "x") {
                busses.add(bus.toLong())
                offsets.add(bus.toLong() - index)
            }
        }

        return chineseRemainder(busses.toLongArray(), offsets.toLongArray())
    }

    // source: https://rosettacode.org/wiki/Chinese_remainder_theorem#Kotlin
    private fun chineseRemainder(n: LongArray, a: LongArray): Long {
        val prod: Long = n.fold(1) { acc, i -> acc * i }
        var sum: Long = 0
        for (i in n.indices) {
            val p = prod / n[i]
            sum += a[i] * multInv(p, n[i]) * p
        }
        return sum % prod
    }

    /* returns x where (a * x) % b == 1 */
    private fun multInv(a: Long, b: Long): Long {
        if (b == 1L) return 1L
        var aa = a
        var bb = b
        var x0 = 0L
        var x1 = 1L
        while (aa > 1) {
            val q = aa / bb
            var t = bb
            bb = aa % bb
            aa = t
            t = x0
            x0 = x1 - q * x0
            x1 = t
        }
        if (x1 < 0) x1 += b
        return x1
    }
}