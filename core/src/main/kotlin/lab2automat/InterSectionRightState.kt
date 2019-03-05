package lab2automat

class InterSectionRightState(override val parser: Parser, override val output: OutputStrategy) : State {
    override fun output(char: Char) {
        when (char) {
            'a' -> {
                parser.changeState(RepeatState(parser, output))
                output.println("state: InterSectionRight, char: a -> ")
            }
            'c' -> {
                parser.changeState(LongRoadState(parser, output))
                output.println("state: IntersectionRight, char: c -> ")
            }
            else -> {
                output.println("Wrong character")
                parser.changeState(StartState(parser, output))
            }
        }
    }
}