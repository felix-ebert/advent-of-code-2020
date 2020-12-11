package days

import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is
import org.junit.Test

class Day9Test {

    private val day = Day9()

    @Test
    fun testPartOne() {
        MatcherAssert.assertThat(day.partOne(), Is.`is`(127))
    }

    @Test
    fun testPartTwo() {
        MatcherAssert.assertThat(day.partTwo(), Is.`is`(62))
    }
}