package lab3regex.automat

import lab2automat.OutputStrategy
import lab3regex.RegexParser

class PState(override val parser: RegexParser, override val output: OutputStrategy) : State {
    override fun output(char: Char, buffer: StringBuilder) {
        when (char) {
            'd' -> {
                buffer.append(char)
                parser.changeState(DPdfState(parser, output))
            }
            else -> {
                buffer.clear()
                parser.changeState(StartState(parser, output))
            }
        }
    }
}