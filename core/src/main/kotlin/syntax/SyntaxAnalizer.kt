package syntax

import scanner.Token

class SyntaxAnalizer(private val tokenArray: List<Token>) {
    private var currentState: State = MainState(this)


    fun scan() {
        for (token in tokenArray) {
            currentState.analyze(token)
        }
    }

    fun changeState(newState: State) {
        currentState = newState
    }
}