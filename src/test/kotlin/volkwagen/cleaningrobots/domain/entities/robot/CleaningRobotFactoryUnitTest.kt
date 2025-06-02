package volkwagen.cleaningrobots.domain.entities.robot

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import volkwagen.cleaningrobots.domain.entities.ground.Ground
import volkwagen.cleaningrobots.domain.entities.shared.Position

class CleaningRobotFactoryUnitTest {
    private val sut = CleaningRobotFactory()

    @Test
    fun `createCleaningRobot should return a cleaningRobot`() {
        // Given
        val ground = Ground(Position(5, 5))
        val position = listOf("1", "2", "N")
        val expected = DefaultCleaningRobot(ground, Position(1, 2), Direction.NORTH)

        // When
        val output = sut.createCleaningRobot(ground, position)

        // Then
        assertEquals(expected, output)
    }
}
