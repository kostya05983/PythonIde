package lab2automat

class OrNotState(override val parser: Parser, override val output: OutputStrategy) : State {

    override fun output(char: Char) {
        when (char) {
            '\\' -> {
                output.println("state: OrNotState, char: \\ ->")
                parser.changeState(EndState(parser, output))
            }
            'b' -> {
                output.println("state: OrNotState, char: b ->")
                parser.changeState(InterSectionRightState(parser, output))
            }
            else -> {
                output.println("Wrong character")
                parser.changeState(StartState(parser, output))
            }
        }
    }
}