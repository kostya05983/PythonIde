package lab3regex.automat

import lab2automat.OutputStrategy
import lab3regex.RegexParser

class FileState(override val parser: RegexParser, override val output: OutputStrategy) : State {
    override fun output(char: Char, buffer: StringBuilder) {
        when (char) {
            'd' -> {
                buffer.append(char)
                parser.changeState(DState(parser, output))
            }
            'y' -> {
                buffer.append(char)
                parser.changeState(YState(parser, output))
            }
            'p' -> {
                buffer.append(char)
                parser.changeState(PState(parser, output))
            }
            else -> {
                buffer.clear()
                parser.changeState(StartState(parser, output))
            }
        }
    }
}