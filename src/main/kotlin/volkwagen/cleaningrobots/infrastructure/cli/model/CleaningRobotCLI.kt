package volkwagen.cleaningrobots.infrastructure.cli.model

import volkwagen.cleaningrobots.application.usecases.GetCleaningRobotDirectionUseCase
import volkwagen.cleaningrobots.application.usecases.GetCleaningRobotPositionUseCase
import volkwagen.cleaningrobots.application.usecases.StartCleaningRobotUseCase
import volkwagen.cleaningrobots.domain.entities.InstructionType
import volkwagen.cleaningrobots.domain.entities.ground.Ground
import volkwagen.cleaningrobots.domain.entities.robot.CleaningRobot
import volkwagen.cleaningrobots.domain.entities.robot.CleaningRobotFactory
import volkwagen.cleaningrobots.domain.entities.shared.Position

class CleaningRobotCLI(
    private val getCleaningRobotPositionUseCase: GetCleaningRobotPositionUseCase = GetCleaningRobotPositionUseCase(),
    private val getCleaningRobotDirectionUseCase: GetCleaningRobotDirectionUseCase = GetCleaningRobotDirectionUseCase(),
    private val startCleaningRobotUseCase: StartCleaningRobotUseCase = StartCleaningRobotUseCase(),
    private val cleaningRobotFactory: CleaningRobotFactory = CleaningRobotFactory(),
) {
    fun start(instructions: Instructions): List<String> {
        val ground = getGround(instructions.commands)
        val instructionByRobot = getInstructionsByRobot(instructions.commands, ground)

        return instructionByRobot.map { (robot, instructions) ->
            startCleaningRobotUseCase.start(robot, instructions)
            val position = getCleaningRobotPositionUseCase.getPosition(robot)
            val direction = getCleaningRobotDirectionUseCase.getDirection(robot)

            "${position.x} ${position.y} ${direction.value}"
        }
    }

    private fun getInstructionsByRobot(
        commands: List<String>,
        ground: Ground,
    ): List<Pair<CleaningRobot, List<InstructionType?>>> =
        commands
            .drop(1)
            .chunked(2)
            .map { line ->
                line
                    .first()
                    .split(SEPARATOR)
                    .let { position ->
                        val robot = cleaningRobotFactory.createCleaningRobot(ground, position)
                        val instructions = line.last().map { InstructionType.from(it.toString()) }

                        robot to instructions
                    }
            }

    private fun getGround(commands: List<String>): Ground =
        commands
            .first()
            .split(SEPARATOR)
            .let { position ->
                Ground(Position(x = position.first().toInt(), y = position.last().toInt()))
            }

    companion object {
        private const val SEPARATOR = " "
    }
}
