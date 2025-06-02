package volkwagen.cleaningrobots.domain.entities.robot

enum class Direction(
    val value: String,
) {
    NORTH("N"),
    SOUTH("S"),
    EAST("E"),
    WEST("W"),
    ;

    companion object {
        fun from(value: String): Direction = entries.first { it.value == value }
    }
}
