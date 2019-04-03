package lab3regex.automat

import lab2automat.OutputStrategy
import lab3regex.RegexParser

class PState(override val parser: RegexParser, override val output: OutputStrategy) : State {
    override fun output(char: Char) {
        when (char) {
            'd' -> {
                parser.changeState(DState(parser, output))
            }
            else -> {
                parser.changeState(StartState(parser, output))
            }
        }
    }
}