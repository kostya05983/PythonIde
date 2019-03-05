package lab2automat

class Parser(val outputStrategy: OutputStrategy) {
    var currentState: State = StartState(this, outputStrategy)

    fun parse(s: String) {
        val sequence = s + "\\"
        for (char in sequence) {
            currentState.output(char)
        }
        if (currentState::class.java == EndState::class.java) {
            currentState.output(' ')
        }

        outputStrategy.println()
    }

    fun changeState(new: State) {
        currentState = new
    }
}