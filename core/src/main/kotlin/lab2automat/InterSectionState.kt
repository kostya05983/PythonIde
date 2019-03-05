package lab2automat

class InterSectionState(override val parser: Parser, override val output: OutputStrategy) : State {
    override fun output(char: Char) {
        when (char) {
            'a' -> {
                output.println("state: Intersection, char: a -> ")
                parser.changeState(OrNotState(parser, output))
            }
            'c' -> {
                output.println("state: Intersection, char: c -> ")
                parser.changeState(LongRoadState(parser, output))
            }
            else -> {
                output.println("Wrong char")
                parser.changeState(StartState(parser, output))
            }
        }
    }
}