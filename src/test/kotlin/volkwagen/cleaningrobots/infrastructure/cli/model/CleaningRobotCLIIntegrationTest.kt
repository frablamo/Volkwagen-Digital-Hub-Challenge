package volkwagen.cleaningrobots.infrastructure.cli.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CleaningRobotCLIIntegrationTest {
    private val sut = CleaningRobotCLI()

    @Test
    fun `initialize should return list of cleaning-robot positions`() {
        // Given
        val instructions =
            Instructions(
                listOf(
                    "5 5",
                    "1 2 N",
                    "LMLMLMLMM",
                    "3 3 E",
                    "MMRMMRMRRM",
                ),
            )

        // When
        val result = sut.start(instructions)

        // Then
        val expected = listOf("1 3 N", "5 1 E")
        assertEquals(expected, result)
    }
}
