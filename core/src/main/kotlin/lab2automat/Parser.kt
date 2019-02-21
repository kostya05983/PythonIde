package lab2automat

class Parser {
    var currentState: State = StartState(this)

    fun parse(s: String) {
        for (char in s) {
            currentState.output(char)
        }
    }

    fun changeState(new: State) {
        currentState = new
    }
}