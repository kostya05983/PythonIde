package lab2automat

class RepeatState(override val parser: Parser, override val output: OutputStrategy) : State {
    override fun output(char: Char) {
        if (char == 'b') {
            output.println("state: RepeatState, char: b -> ")
            parser.changeState(InterSectionRightState(parser, output))
        } else {
            output.println("Wrong character")
            parser.changeState(StartState(parser, output))
        }
    }
}