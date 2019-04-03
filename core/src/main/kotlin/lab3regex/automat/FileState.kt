package lab3regex.automat

import lab2automat.OutputStrategy
import lab3regex.RegexParser

class FileState(override val parser: RegexParser, override val output: OutputStrategy) : State {
    override fun output(char: Char) {
        when (char) {
            'd' -> {
                parser.changeState(DState(parser, output))
            }
            'y' -> {
                parser.changeState(YState(parser, output))
            }
            'p' -> {
                parser.changeState(PState(parser, output))
            }
            else -> {
                parser.changeState(StartState(parser, output))
            }
        }
    }
}