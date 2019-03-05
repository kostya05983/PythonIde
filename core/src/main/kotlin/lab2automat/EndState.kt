package lab2automat

class EndState(override val parser: Parser, override val output: OutputStrategy) : State {
    override fun output(char: Char) {
        output.println("success")
        parser.changeState(StartState(parser, output))
    }
}