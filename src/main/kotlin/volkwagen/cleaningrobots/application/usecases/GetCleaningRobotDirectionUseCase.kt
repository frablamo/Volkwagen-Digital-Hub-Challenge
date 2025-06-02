package volkwagen.cleaningrobots.application.usecases

import volkwagen.cleaningrobots.domain.entities.robot.CleaningRobot
import volkwagen.cleaningrobots.domain.entities.robot.Direction

class GetCleaningRobotDirectionUseCase {
    fun getDirection(cleaningRobot: CleaningRobot): Direction = cleaningRobot.getDirection()
}
