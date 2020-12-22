package days

import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is
import org.junit.Test

class Day18Test {

    private val day = Day18()

    @Test
    fun testPartOne() {
        MatcherAssert.assertThat(day.partOne(), Is.`is`(122L))
    }
}