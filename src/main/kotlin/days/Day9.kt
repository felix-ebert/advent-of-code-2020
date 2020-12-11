package days

class Day9 : Day(9) {
    override fun partOne(): Any {
        val input = inputList.map { it.toLong() }.toTypedArray()
        val preamble = 25
        val pairs = hashSetOf<Long>()

        for (i in preamble until input.size) {
            val s = hashSetOf<Long>()
            for (k in 1..preamble) {
                val temp = input[i] - input[i - k]
                if (s.contains(temp)) {
                    pairs.add(input[i])
                }
                s.add(input[i - k])
            }
        }

        return (input.copyOfRange(preamble, input.size) subtract pairs).first()
    }

    override fun partTwo(): Any {
        val invalidNumber: Long = 1504371145
        val input = inputList.map { it.toLong() }.toTypedArray()

        for (i in input.indices) {
            var sum = input[i]
            for (k in i + 1 until input.size - 2) {
                if (sum == invalidNumber) {
                    val encryptionSet = input.copyOfRange(i, k-1)
                    return (encryptionSet.minOrNull()!! + encryptionSet.maxOrNull()!!).toInt()
                }
                if (sum > invalidNumber || k.toLong() == invalidNumber) break
                sum += input[k]
            }
        }

        return 0
    }
}