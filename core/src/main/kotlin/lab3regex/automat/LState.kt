package lab3regex.automat

import lab2automat.OutputStrategy
import lab3regex.RegexParser

class LState(override val parser: RegexParser, override val output: OutputStrategy) : State {
    override fun output(char: Char, buffer: StringBuilder) {
//        buffer.append(char)
        output.println(buffer.toString())
        buffer.clear()
        parser.changeState(StartState(parser, output))
    }
}