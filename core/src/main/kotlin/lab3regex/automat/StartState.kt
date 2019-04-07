package lab3regex.automat

import lab2automat.OutputStrategy
import lab3regex.RegexParser

class StartState(override val parser: RegexParser, override val output: OutputStrategy) : State {
    override fun output(char: Char, buffer: StringBuilder) {
        when (char) {
            '.' -> {
                buffer.append(char)
                parser.changeState(FileState(parser, output))
            }
            ' '-> {
                buffer.clear()
            }
            else -> {
                buffer.append(char)
            }
        }
    }
}