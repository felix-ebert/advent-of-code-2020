package days

import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is
import org.junit.Test

class Day13Test {

    private val day = Day13()

    @Test
    fun testPartOne() {
        MatcherAssert.assertThat(day.partOne(), Is.`is`(295))
    }

    @Test
    fun testPartTwo() {
        MatcherAssert.assertThat(day.partTwo(), Is.`is`(1068781L))
    }
}