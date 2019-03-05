package lab2automat

class LongRoadState(override val parser: Parser, override val output: OutputStrategy) : State {
    override fun output(char: Char) {
        when (char) {
            '\\' -> {
                output.println("state: LongRoad, char: \\ ->")
                parser.changeState(EndState(parser, output))
            }
            else -> {
                output.println("Wrong character")
                parser.changeState(StartState(parser, output))
            }
        }
    }
}