package volkwagen.cleaningrobots.domain.entities.robot

import volkwagen.cleaningrobots.domain.entities.Rotation
import volkwagen.cleaningrobots.domain.entities.shared.Position

interface CleaningRobot {
    fun move()

    fun rotate(rotation: Rotation)

    fun getPosition(): Position

    fun getDirection(): Direction
}
