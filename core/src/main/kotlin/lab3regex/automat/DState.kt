package lab3regex.automat

import lab2automat.OutputStrategy
import lab3regex.RegexParser

class DState(override val parser: RegexParser, override val output: OutputStrategy) : State {
    override fun output(char: Char) {
        when (char) {
            'f' -> {
                parser.changeState(FState(parser, output))
            }
            'o' -> {
                parser.changeState(OState(parser, output))
            }
            else -> {
                parser.changeState(StartState(parser, output))
            }
        }
    }
}