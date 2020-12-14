package days

import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is
import org.junit.Test

class Day11Test {

    private val day = Day11()

    @Test
    fun testPartOne() {
        MatcherAssert.assertThat(day.partOne(), Is.`is`(37))
    }

    @Test
    fun testPartTwo() {
        MatcherAssert.assertThat(day.partTwo(), Is.`is`(26))
    }
}