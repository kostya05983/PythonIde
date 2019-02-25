package lab2automat

class LongRoadState(override val parser: Parser, override val output: OutputStrategy) : State {
    override fun output(char: Char) {
        when (char) {
            '\\' -> {
                output.print("state: LongRoad, char: \\ ->")
                parser.changeState(EndState(parser, output))
            }
            else -> {
                output.print("Wrong character")
                parser.changeState(StartState(parser, output))
            }
        }
    }
}