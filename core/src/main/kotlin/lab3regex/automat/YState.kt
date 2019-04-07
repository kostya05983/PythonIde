package lab3regex.automat

import lab2automat.OutputStrategy
import lab3regex.RegexParser

class YState(override val parser: RegexParser, override val output: OutputStrategy) : State {
    override fun output(char: Char, buffer: StringBuilder) {
        when (char) {
            'a' -> {
                buffer.append(char)
                parser.changeState(AState(parser, output))
            }
            else -> {
                buffer.clear()
                parser.changeState(StartState(parser, output))
            }
        }
    }
}