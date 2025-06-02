package volkwagen.cleaningrobots.infrastructure.cli.model

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import volkwagen.cleaningrobots.application.usecases.GetCleaningRobotDirectionUseCase
import volkwagen.cleaningrobots.application.usecases.GetCleaningRobotPositionUseCase
import volkwagen.cleaningrobots.application.usecases.StartCleaningRobotUseCase
import volkwagen.cleaningrobots.domain.entities.ground.Ground
import volkwagen.cleaningrobots.domain.entities.robot.CleaningRobotFactory
import volkwagen.cleaningrobots.domain.entities.robot.DefaultCleaningRobot
import volkwagen.cleaningrobots.domain.entities.robot.Direction
import volkwagen.cleaningrobots.domain.entities.shared.Position

class CleaningRobotCLIUnitTest {
    private val getCleaningRobotPositionUseCase: GetCleaningRobotPositionUseCase = mockk()
    private val getCleaningRobotDirectionUseCase: GetCleaningRobotDirectionUseCase = mockk()
    private val startCleaningRobotUseCase: StartCleaningRobotUseCase = mockk()
    private val robotFactory: CleaningRobotFactory = mockk()

    private val sut =
        CleaningRobotCLI(
            getCleaningRobotPositionUseCase,
            getCleaningRobotDirectionUseCase,
            startCleaningRobotUseCase,
            robotFactory,
        )

    @Test
    fun `start should return list of cleaning robot positions`() {
        // Given
        val instructions =
            Instructions(
                listOf(
                    "5 5",
                    "1 2 N",
                    "LFLFLFLFF",
                ),
            )

        val ground = Ground(Position(5, 5))
        val robot = DefaultCleaningRobot(ground, Position(1, 2), Direction.NORTH)

        every { robotFactory.createCleaningRobot(ground, listOf("1", "2", "N")) } returns robot
        every { getCleaningRobotDirectionUseCase.getDirection(any()) } returns Direction.NORTH
        every { getCleaningRobotPositionUseCase.getPosition(any()) } returns Position(1, 3)
        every { startCleaningRobotUseCase.start(robot, any()) } returns Unit

        // When
        val result = sut.start(instructions)

        // Then
        val expected = listOf("1 3 N")
        assertEquals(expected, result)
    }
}
