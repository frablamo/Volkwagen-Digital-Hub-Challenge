package volkwagen.cleaningrobots.domain.entities

enum class InstructionType(
    val value: String,
) {
    LEFT("L"),
    RIGHT("R"),
    FORWARD("M"),
    ;

    companion object {
        fun from(value: String): InstructionType? = entries.firstOrNull { it.value == value }
    }
}
