package volkwagen.cleaningrobots.domain.entities.robot

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import volkwagen.cleaningrobots.domain.entities.Rotation
import volkwagen.cleaningrobots.domain.entities.Rotation.LEFT
import volkwagen.cleaningrobots.domain.entities.Rotation.RIGHT
import volkwagen.cleaningrobots.domain.entities.ground.Ground
import volkwagen.cleaningrobots.domain.entities.robot.Direction.EAST
import volkwagen.cleaningrobots.domain.entities.robot.Direction.NORTH
import volkwagen.cleaningrobots.domain.entities.robot.Direction.SOUTH
import volkwagen.cleaningrobots.domain.entities.robot.Direction.WEST
import volkwagen.cleaningrobots.domain.entities.shared.Position
import java.util.stream.Stream

class DefaultCleaningRobotUnitTest {
    @ParameterizedTest
    @MethodSource("validMovements")
    fun `move works inside ground grid`(
        direction: Direction,
        expectedPosition: Position,
    ) {
        // Given
        val ground = Ground(Position(5, 5))
        val robot = DefaultCleaningRobot(ground, Position(1, 1), direction)

        // When
        robot.move()

        // Then
        assertEquals(expectedPosition, robot.getPosition())
    }

    @ParameterizedTest
    @MethodSource("invalidMovements")
    fun `move is not possible outside plateau grid`(
        direction: Direction,
        expectedPosition: Position,
    ) {
        // Given
        val ground = Ground(Position(0, 0))
        val robot = DefaultCleaningRobot(ground, Position(0, 0), direction)

        // When
        robot.move()

        // Then
        assertEquals(expectedPosition, robot.getPosition())
    }

    @ParameterizedTest
    @MethodSource("rotateMovements")
    fun `rotate left works`(
        rotation: Rotation,
        direction: Direction,
        expectedDirection: Direction,
    ) {
        // Given
        val ground = Ground(Position(5, 5))
        val robot = DefaultCleaningRobot(ground, Position(1, 1), direction)

        // When
        robot.rotate(rotation)

        // Then
        assertEquals(expectedDirection, robot.getDirection())
    }

    @Test
    fun `getPosition returns current position`() {
        // Given
        val ground = Ground(Position(5, 5))
        val robot = DefaultCleaningRobot(ground, Position(1, 1), NORTH)

        // When
        val position = robot.getPosition()

        // Then
        assertEquals(Position(1, 1), position)
    }

    @Test
    fun `getDirection returns current direction`() {
        // Given
        val ground = Ground(Position(5, 5))
        val robot = DefaultCleaningRobot(ground, Position(1, 1), NORTH)

        // When
        val direction = robot.getDirection()

        // Then
        assertEquals(NORTH, direction)
    }

    companion object {
        @JvmStatic
        private fun validMovements() =
            Stream.of(
                Arguments.of(NORTH, Position(1, 2)),
                Arguments.of(SOUTH, Position(1, 0)),
                Arguments.of(WEST, Position(0, 1)),
                Arguments.of(EAST, Position(2, 1)),
            )

        @JvmStatic
        private fun invalidMovements() =
            Stream.of(
                Arguments.of(NORTH, Position(0, 0)),
                Arguments.of(SOUTH, Position(0, 0)),
                Arguments.of(WEST, Position(0, 0)),
                Arguments.of(EAST, Position(0, 0)),
            )

        @JvmStatic
        private fun rotateMovements() =
            Stream.of(
                Arguments.of(LEFT, NORTH, WEST),
                Arguments.of(LEFT, WEST, SOUTH),
                Arguments.of(LEFT, SOUTH, EAST),
                Arguments.of(LEFT, EAST, NORTH),
                Arguments.of(RIGHT, NORTH, EAST),
                Arguments.of(RIGHT, EAST, SOUTH),
                Arguments.of(RIGHT, SOUTH, WEST),
                Arguments.of(RIGHT, WEST, NORTH),
            )
    }
}
