package days

class Day2 : Day(2) {
    override fun partOne(): Int {
        return inputList.count { validatePolicyOne(it) }
    }

    override fun partTwo(): Any {
        return inputList.count { validatePolicyTwo(it) }
    }

    data class PasswordLine(val policyBoundaries: IntRange, val policyCharacter: String, val password: String)

    private fun parseInput(input: String): PasswordLine {
        val parts = input.split(" ")
        val policyBoundaries = IntRange(parts[0].substringBefore("-").toInt(), parts[0].substringAfter("-").toInt())
        val policyCharacter = parts[1].substringBefore(":")
        val password = parts[2]
        return PasswordLine(policyBoundaries, policyCharacter, password)
    }

    private fun validatePolicyOne(input: String): Boolean {
        val ln = parseInput(input)
        val occurrence = ln.password.count { ln.policyCharacter.contains(it) }
        return ln.policyBoundaries.contains(occurrence)
    }

    private fun validatePolicyTwo(input: String): Boolean {
        val ln = parseInput(input)
        return (ln.password[ln.policyBoundaries.first - 1].toString() == ln.policyCharacter &&
                ln.password[ln.policyBoundaries.last - 1].toString() != ln.policyCharacter) ||
                (ln.password[ln.policyBoundaries.first - 1].toString() != ln.policyCharacter &&
                        ln.password[ln.policyBoundaries.last - 1].toString() == ln.policyCharacter)
    }
}