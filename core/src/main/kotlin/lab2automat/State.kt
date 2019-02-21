package lab2automat

interface State {
    val parser: Parser
    val output: OutputStrategy

    fun output(char: Char)
}