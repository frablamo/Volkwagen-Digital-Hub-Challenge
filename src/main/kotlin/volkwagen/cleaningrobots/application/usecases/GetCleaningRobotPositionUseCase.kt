package volkwagen.cleaningrobots.application.usecases

import volkwagen.cleaningrobots.domain.entities.robot.CleaningRobot
import volkwagen.cleaningrobots.domain.entities.shared.Position

class GetCleaningRobotPositionUseCase {
    fun getPosition(cleaningRobot: CleaningRobot): Position = cleaningRobot.getPosition()
}
