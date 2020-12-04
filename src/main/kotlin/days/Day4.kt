package days

class Day4 : Day(4) {

    override fun partOne(): Any {
        val passwords = inputString.split("\n\n")
        return passwords.count { validatePolicyOne(it) }
    }

    override fun partTwo(): Any {
        val passwords = inputString.split("\n\n")
        return passwords.count { validatePolicyTwo(it) }
    }

    private fun validatePolicyOne(password: String): Boolean {
        val policies = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
        return policies.stream().allMatch(password::contains)
    }

    private fun validatePolicyTwo(password: String): Boolean {
        val fields = password.replace("\n", " ")
            .split(" ")
            .map { it.split(":") }
            .map { it.first() to it.last() }
            .toMap()

        return isValidYear(fields.getOrDefault("byr", "0"), 1920, 2002) &&
                isValidYear(fields.getOrDefault("iyr", "0"), 2010, 2020) &&
                isValidYear(fields.getOrDefault("eyr", "0"), 2020, 2030) &&
                isValidHeight(fields.getOrDefault("hgt", "")) &&
                isValidHairColor(fields.getOrDefault("hcl", "")) &&
                isValidEyeColor(fields.getOrDefault("ecl", "")) &&
                isValidPassportId(fields.getOrDefault("pid", ""))
    }

    private fun isValidYear(year: String, least: Int, most: Int): Boolean {
        return year.toInt() in least..most
    }

    private fun isValidHeight(height: String): Boolean {
        return (height.contains("cm") && height.substringBefore("cm").toInt() in 150..193) ||
                (height.contains("in") && height.substringBefore("in").toInt() in 59..76)
    }

    private fun isValidHairColor(color: String): Boolean {
        return color.matches(Regex("#-?[0-9a-fA-F]+"))
    }

    private fun isValidEyeColor(color: String): Boolean {
        return listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(color)
    }

    private fun isValidPassportId(id: String): Boolean {
        return id.matches(Regex("^(?=\\d{9}\$)\\d*[1-9]\\d*"))
    }
}