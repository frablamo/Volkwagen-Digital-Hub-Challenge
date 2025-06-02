package volkwagen.cleaningrobots.domain.entities.robot

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class DirectionUnitTest {

    @ParameterizedTest
    @MethodSource("directions")
    fun `from value return expected direction`(value: String, expected: Direction?) {
        // When
        val direction = Direction.from(value)

        // Then
        assert(direction == expected)
    }

    companion object {
        @JvmStatic
        fun directions() = listOf(
            Arguments.of("N", Direction.NORTH),
            Arguments.of("S", Direction.SOUTH),
            Arguments.of("E", Direction.EAST),
            Arguments.of("W", Direction.WEST),
        )
    }
}