package lab2automat

class InterSectionState(override val parser: Parser, override val output: OutputStrategy) : State {
    override fun output(char: Char) {
        when (char) {
            'a' -> {
                output.print("state: Intersection, char: a ->")
                parser.changeState(OrNotState(parser, output))
            }
            'c' -> {
                output.print("state: Intersection, char: c ->")
                parser.changeState(LongRoadState(parser, output))
            }
            else -> {
                output.print("Wrong char")
                parser.changeState(StartState(parser, output))
            }
        }
    }
}