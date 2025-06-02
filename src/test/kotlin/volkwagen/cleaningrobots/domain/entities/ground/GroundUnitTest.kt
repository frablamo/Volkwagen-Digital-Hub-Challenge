package volkwagen.cleaningrobots.domain.entities.ground

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import volkwagen.cleaningrobots.domain.entities.shared.Position

class GroundUnitTest {

    private val sut = Ground(Position(1, 2))

    @Test
    fun `getPositionX should return the x coordinate of the ground position`() {
        // When
        val positionX = sut.getPositionX()

        // Then
        assertEquals(1, positionX)
    }

    @Test
    fun `getPositionY should return the y coordinate of the ground position`() {
        // When
        val positionY = sut.getPositionY()

        // Then
        assertEquals(2, positionY)
    }
}