package volkwagen.cleaningrobots.domain.entities

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class InstructionTypeUnitTest {

    @ParameterizedTest
    @MethodSource("instructions")
    fun `from value return expected instruction`(value: String, expected: InstructionType?) {
        // When
        val instructionType = InstructionType.from(value)

        // Then
        assert(instructionType == expected)
    }

    companion object {
        @JvmStatic
        fun instructions() = listOf(
            Arguments.of("L", InstructionType.LEFT),
            Arguments.of("R", InstructionType.RIGHT),
            Arguments.of("M", InstructionType.FORWARD),
            Arguments.of("X", null),
        )
    }
}