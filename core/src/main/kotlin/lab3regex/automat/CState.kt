package lab3regex.automat

import lab2automat.OutputStrategy
import lab3regex.RegexParser

class CState(override val parser: RegexParser, override val output: OutputStrategy) : State {
    override fun output(char: Char) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}