package syntax

import scanner.Token
import java.util.*

class SyntaxAnalizer(private val tokenArray: List<Token>) {
    private var currentState: State = MainState(this, Stack())


    fun scan() {
        for (token in tokenArray) {
            currentState.analyze(token)
        }
    }

    fun changeState(newState: State) {
        currentState = newState
    }
}