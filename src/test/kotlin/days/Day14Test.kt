package days

import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is
import org.junit.Test
import java.math.BigInteger

class Day14Test {

    private val day = Day14()

    @Test
    fun testPartOne() {
        MatcherAssert.assertThat(day.partOne(), Is.`is`(165))
    }

    @Test
    fun testPartTwo() {
        MatcherAssert.assertThat(day.partTwo(), Is.`is`(BigInteger.valueOf(208)))
    }
}