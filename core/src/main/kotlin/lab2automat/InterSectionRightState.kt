package lab2automat

class InterSectionRightState(override val parser: Parser, override val output: OutputStrategy) : State {
    override fun output(char: Char) {
        when (char) {
            'a' -> {
                parser.changeState(RepeatState(parser, output))
                output.print("state: InterSectionRight, char: a -> ")
            }
            'c' -> {
                parser.changeState(LongRoadState(parser, output))
                output.print("state: IntersectionRight, char: c -> ")
            }
            else -> {
                output.print("Wrong character")
                parser.changeState(StartState(parser, output))
            }
        }
    }
}