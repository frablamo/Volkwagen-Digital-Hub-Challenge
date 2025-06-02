package volkwagen.cleaningrobots.application.usecases

import volkwagen.cleaningrobots.domain.entities.InstructionType
import volkwagen.cleaningrobots.domain.entities.Rotation
import volkwagen.cleaningrobots.domain.entities.robot.CleaningRobot
import java.io.PrintStream

class StartCleaningRobotUseCase(
    private val out: PrintStream = System.out,
) {
    fun start(
        cleaningRobot: CleaningRobot,
        instructions: List<InstructionType?>,
    ): Unit =
        instructions
            .forEach {
                executeInstruction(cleaningRobot, it)
            }

    private fun executeInstruction(
        cleaningRobot: CleaningRobot,
        instruction: InstructionType?,
    ) = when (instruction) {
        InstructionType.LEFT -> cleaningRobot.rotate(Rotation.LEFT)
        InstructionType.RIGHT -> cleaningRobot.rotate(Rotation.RIGHT)
        InstructionType.FORWARD -> cleaningRobot.move()
        null -> out.println(ERROR_MESSAGE)
    }

    companion object {
        private const val ERROR_MESSAGE = "Skipping wrong instruction"
    }
}
