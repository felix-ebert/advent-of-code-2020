package days

import java.math.BigInteger

class Day14 : Day(14) {

    override fun partOne(): Any {
        val memory = mutableMapOf<String, String>()
        var mask = ""

        for (line in inputList) {
            if (line.startsWith("mask"))
                mask = line.substringAfterLast(" ")
            else {
                memory[line.substringAfter("[").substringBefore("]")] =
                    overrideWithMask(line.substringAfterLast(" "), mask)
            }
        }

        return memory.values.sumOf { BigInteger(it, 2) }
    }

    private fun overrideWithMask(value: String, mask: String): String {
        val binaryValue = Integer.toBinaryString(value.toInt()).padStart(36, '0').toMutableList()

        for (bit in mask.indices) {
            if (mask[bit] != 'X') binaryValue[bit] = mask[bit]
        }

        return binaryValue.joinToString("")
    }

    override fun partTwo(): Any {
        return 0
    }
}