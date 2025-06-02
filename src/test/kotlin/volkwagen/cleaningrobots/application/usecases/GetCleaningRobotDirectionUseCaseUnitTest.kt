package volkwagen.cleaningrobots.application.usecases

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import volkwagen.cleaningrobots.domain.entities.robot.CleaningRobot
import volkwagen.cleaningrobots.domain.entities.robot.Direction

class GetCleaningRobotDirectionUseCaseUnitTest {

    private val sut = GetCleaningRobotDirectionUseCase()

    @Test
    fun `getDirection should return the direction of the cleaning robot`() {
        // Given
        val cleaningRobot: CleaningRobot = mockk()
        every { cleaningRobot.getDirection() } returns ANY_DIRECTION

        // When
        val direction = sut.getDirection(cleaningRobot)

        // Then
        assertEquals(ANY_DIRECTION, direction)
        verify(exactly = 1) { cleaningRobot.getDirection() }
    }

    companion object {
        private val ANY_DIRECTION = Direction.NORTH
    }
}