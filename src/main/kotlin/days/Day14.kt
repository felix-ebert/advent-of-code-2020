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
                val value = line.substringAfterLast(" ")
                val binaryValue = Integer.toBinaryString(value.toInt()).padStart(36, '0').toMutableList()
                val address = line.substringAfter("[").substringBefore("]")

                for (bit in mask.indices) {
                    if (mask[bit] != 'X') binaryValue[bit] = mask[bit]
                }

                memory[address] = binaryValue.joinToString("")
            }
        }

        return memory.values.sumOf { BigInteger(it, 2) }
    }

    override fun partTwo(): Any {
        val memory = mutableMapOf<String, String>()
        var mask = ""

        for (line in inputList) {
            if (line.startsWith("mask"))
                mask = line.substringAfterLast(" ")
            else {
                val value = line.substringAfterLast(" ")
                val address = line.substringAfter("[").substringBefore("]")
                val binaryAddress = Integer.toBinaryString(address.toInt()).padStart(36, '0').toMutableList()

                for (bit in mask.indices) {
                    if (mask[bit] != '0') binaryAddress[bit] = mask[bit]
                }

                getFloatingAddresses(binaryAddress.joinToString(""), mutableListOf()).forEach {
                    memory[it] = value
                }
            }
        }

        return memory.values.sumOf { it.toBigInteger() }
    }

    private fun getFloatingAddresses(address: String, addresses: MutableList<String>): MutableList<String> {
        if (address.contains('X')) {
            getFloatingAddresses(address.replaceFirst('X', '0'), addresses)
            getFloatingAddresses(address.replaceFirst('X', '1'), addresses)
        } else {
            addresses.add(address)
        }
        return addresses
    }
}