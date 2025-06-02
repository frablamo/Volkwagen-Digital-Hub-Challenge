package volkwagen.cleaningrobots.application.usecases

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import volkwagen.cleaningrobots.domain.entities.robot.CleaningRobot
import volkwagen.cleaningrobots.domain.entities.shared.Position

class GetCleaningRobotPositionUseCaseUnitTest {

    private val sut = GetCleaningRobotPositionUseCase()

    @Test
    fun `getDirection should return the direction of the cleaning robot`() {
        // Given
        val cleaningRobot: CleaningRobot = mockk()
        every { cleaningRobot.getPosition() } returns ANY_POSITION

        // When
        val direction = sut.getPosition(cleaningRobot)

        // Then
        assertEquals(ANY_POSITION, direction)
        verify(exactly = 1) { cleaningRobot.getPosition() }
    }

    companion object {
        private val ANY_POSITION = Position(0, 0)
    }
}