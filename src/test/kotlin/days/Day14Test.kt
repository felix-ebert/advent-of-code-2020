package days

import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is
import org.junit.Test

class Day14Test {

    private val day = Day14()

    @Test
    fun testPartOne() {
        MatcherAssert.assertThat(day.partOne(), Is.`is`(165))
    }
}