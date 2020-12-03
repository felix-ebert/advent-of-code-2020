package days

import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is
import org.junit.Test

class Day3Test {

    private val day = Day3()

    @Test
    fun testPartOne() {
        MatcherAssert.assertThat(day.partOne(), Is.`is`(7))
    }

    @Test
    fun testPartTwo() {
        MatcherAssert.assertThat(day.partTwo(), Is.`is`(336))
    }
}