package days

import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is
import org.junit.Test

class Day2Test {

    private val day = Day2()

    @Test
    fun testPartOne() {
        MatcherAssert.assertThat(day.partOne(), Is.`is`(2))
    }

    @Test
    fun testPartTwo() {
        MatcherAssert.assertThat(day.partTwo(), Is.`is`(1))
    }
}