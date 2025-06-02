package volkwagen.cleaningrobots

import volkwagen.cleaningrobots.infrastructure.cli.model.CleaningRobotCLI
import volkwagen.cleaningrobots.infrastructure.cli.model.Instructions

fun main() {
    val robotCli = CleaningRobotCLI()
    println("Hello Volkwagen Digital Hub Reviewers")
    println("-------------------------------------")
    println("Please enter the input by stdin. A blank line will be considered end of instructions and start the app")
    val input = mutableListOf<String>()
    var line = readlnOrNull()
    while (!line.isNullOrBlank()) {
        input.add(line)
        line = readlnOrNull()
    }
    println(" \nOutput:")
    robotCli.start(Instructions(input)).forEach(::println)
}
