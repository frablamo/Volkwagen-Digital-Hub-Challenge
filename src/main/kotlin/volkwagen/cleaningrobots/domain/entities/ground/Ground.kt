package volkwagen.cleaningrobots.domain.entities.ground

import volkwagen.cleaningrobots.domain.entities.shared.Position

data class Ground(
    val position: Position,
) {
    fun getPositionX(): Int = position.x

    fun getPositionY(): Int = position.y
}
