package volkwagen.cleaningrobots.application.usecases

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import volkwagen.cleaningrobots.domain.entities.InstructionType
import volkwagen.cleaningrobots.domain.entities.Rotation
import volkwagen.cleaningrobots.domain.entities.robot.CleaningRobot
import java.io.PrintStream

class StartCleaningRobotUseCaseUnitTest {

    private val cleaningRobot: CleaningRobot = mockk()
    private val printStream: PrintStream = mockk()

    private val sut = StartCleaningRobotUseCase(printStream)

    @Test
    fun `start cleaningRobot executes left rotation`() {
        // Given
        val instructions = listOf(InstructionType.LEFT)
        every { cleaningRobot.rotate(Rotation.LEFT) } returns Unit

        // When
        sut.start(cleaningRobot, instructions)

        // Then
        verify { cleaningRobot.rotate(Rotation.LEFT) }
    }

    @Test
    fun `start cleaningRobot executes right rotation`() {
        // Given
        val instructions = listOf(InstructionType.RIGHT)
        every { cleaningRobot.rotate(Rotation.RIGHT) } returns Unit

        // When
        sut.start(cleaningRobot, instructions)

        // Then
        verify { cleaningRobot.rotate(Rotation.RIGHT) }
    }

    @Test
    fun `start cleaningRobot executes forward rotation`() {
        // Given
        val instructions = listOf(InstructionType.FORWARD)
        every { cleaningRobot.move() } returns Unit

        // When
        sut.start(cleaningRobot, instructions)

        // Then
        verify { cleaningRobot.move() }
    }

    @Test
    fun `start cleaningRobot prints an error`() {
        // Given
        val instructions = listOf(null)
        every { printStream.println(any<String>()) } returns Unit

        // When
        sut.start(cleaningRobot, instructions)

        // Then
        verify { printStream.println(any<String>()) }
    }
}