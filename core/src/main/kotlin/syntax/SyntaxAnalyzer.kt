package syntax

import scanner.Token
import scanner.Tokens
import java.util.*

class SyntaxAnalyzer() {
    private var currentState: State = MainState(this, Stack(), listOf())

    fun analyze(tokens: List<Token>): List<Token> {
        for (token in tokens) {
            currentState.analyze(token)
        }

        val lastToken = currentState.memory.peek()
        if (lastToken == Tokens.COLON && lastToken == Tokens.NEWLINE) {
            TODO("Error if without statement")
        }
        return currentState.errorTokens
    }

    fun changeState(newState: State) {
        currentState = newState
    }
}