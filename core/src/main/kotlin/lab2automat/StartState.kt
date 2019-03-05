package lab2automat

class StartState(override val parser: Parser, override val output: OutputStrategy) : State {
    override fun output(char: Char) {
        if (char == 'a') {
            output.println("s: Start, char: a -> ")
            parser.changeState(InterSectionState(parser, output))
        } else {
            output.println("Wrong character")
            parser.changeState(StartState(parser, output))
        }
    }
}