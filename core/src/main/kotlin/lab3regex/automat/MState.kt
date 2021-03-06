package lab3regex.automat

import lab2automat.OutputStrategy
import lab3regex.RegexParser

class MState(override val parser: RegexParser, override val output: OutputStrategy) : State {
    override fun output(char: Char, buffer: StringBuilder) {
        when (char) {
            'l' -> {
                buffer.append(char)
                parser.changeState(LState(parser, output))
            }
            else -> {
                buffer.clear()
                parser.changeState(StartState(parser, output))
            }
        }
    }
}