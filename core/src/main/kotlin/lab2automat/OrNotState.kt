package lab2automat

class OrNotState(override val parser: Parser, override val output: OutputStrategy) : State {

    override fun output(char: Char) {
        when (char) {
            '\\' -> {
                output.print("state: OrNotState, char: \\")
                parser.changeState(EndState(parser, output))
            }
            'b' -> {
                output.print("state:OrNotState, char: b")
                parser.changeState(InterSectionRightState(parser, output))
            }
            else -> {
                output.print("Wrong character")
                parser.changeState(StartState(parser, output))
            }
        }
    }
}