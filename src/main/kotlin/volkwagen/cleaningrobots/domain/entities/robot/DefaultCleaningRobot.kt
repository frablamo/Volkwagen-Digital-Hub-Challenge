package volkwagen.cleaningrobots.domain.entities.robot

import volkwagen.cleaningrobots.domain.entities.Rotation
import volkwagen.cleaningrobots.domain.entities.Rotation.RIGHT
import volkwagen.cleaningrobots.domain.entities.ground.Ground
import volkwagen.cleaningrobots.domain.entities.robot.Direction.*
import volkwagen.cleaningrobots.domain.entities.shared.Position
import kotlin.math.max
import kotlin.math.min

data class DefaultCleaningRobot(
    private val ground: Ground,
    private var position: Position,
    private var direction: Direction,
) : CleaningRobot {
    override fun move() {
        position =
            when (direction) {
                NORTH -> position.copy(y = min(position.y + 1, ground.getPositionY()))
                EAST -> position.copy(x = min(position.x + 1, ground.getPositionX()))
                SOUTH -> position.copy(y = max(position.y - 1, 0))
                WEST -> position.copy(x = max(position.x - 1, 0))
            }
    }

    override fun rotate(rotation: Rotation) {
        direction =
            when (rotation) {
                Rotation.LEFT ->
                    when (direction) {
                        NORTH -> WEST
                        EAST -> NORTH
                        SOUTH -> EAST
                        WEST -> SOUTH
                    }

                RIGHT ->
                    when (direction) {
                        NORTH -> EAST
                        EAST -> SOUTH
                        SOUTH -> WEST
                        WEST -> NORTH
                    }
            }
    }

    override fun getPosition(): Position = position

    override fun getDirection(): Direction = direction
}
