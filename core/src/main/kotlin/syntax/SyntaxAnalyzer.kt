package syntax

import scanner.Token
import scanner.Tokens
import java.util.*

class SyntaxAnalyzer(private val tokenArray: List<Token>) {
    private var currentState: State = MainState(this, Stack())


    fun analyze() {
        for (token in tokenArray) {
            currentState.analyze(token)
        }

        val lastToken = currentState.memory.peek()
        if (lastToken == Tokens.COLON && lastToken == Tokens.NEWLINE) {
            TODO("Error if without statement")
        }
    }

    fun changeState(newState: State) {
        currentState = newState
    }
}