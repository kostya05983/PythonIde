package lab2automat

class StartState(override val parser: Parser, override val output: OutputStrategy) : State {
    override fun output(char: Char) {
        if (char == 'a') {
            print("s:Start,char:a ->")
        } else {
            println("Wrong character")
        }
    }
}