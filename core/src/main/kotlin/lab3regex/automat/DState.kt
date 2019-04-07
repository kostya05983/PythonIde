package lab3regex.automat

import lab2automat.OutputStrategy
import lab3regex.RegexParser

class DState(override val parser: RegexParser, override val output: OutputStrategy) : State {
    override fun output(char: Char, buffer: StringBuilder) {
        when (char) {
            'o' -> {
                buffer.append(char)
                parser.changeState(OState(parser, output))
            }
            else -> {
                buffer.clear()
                parser.changeState(StartState(parser, output))
            }
        }
    }
}