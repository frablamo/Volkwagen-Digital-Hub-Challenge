package volkwagen.cleaningrobots.domain.entities.robot

import volkwagen.cleaningrobots.domain.entities.ground.Ground
import volkwagen.cleaningrobots.domain.entities.shared.Position

class CleaningRobotFactory {
    fun createCleaningRobot(
        ground: Ground,
        position: List<String>,
    ): CleaningRobot =
        DefaultCleaningRobot(
            ground = ground,
            position = Position(position.first().toInt(), position.second().toInt()),
            direction = Direction.from(position.last()),
        )
}

private fun <E> List<E>.second(): String = this[1].toString()
