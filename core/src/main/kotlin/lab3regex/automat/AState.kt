package lab3regex.automat

import lab2automat.OutputStrategy
import lab3regex.RegexParser

class AState(override val parser: RegexParser, override val output: OutputStrategy) : State {
    override fun output(char: Char) {
        when (char) {
            'm' -> {
                parser.changeState(MState(parser, output))
            }
            else -> {
                parser.changeState(StartState(parser, output))
            }
        }
    }
}