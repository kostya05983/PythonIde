package syntax

import scanner.Token
import scanner.Tokens
import java.util.*

class SyntaxAnalyzer {
    private lateinit var currentState: State

    fun analyze(tokens: List<Token>): List<Token> {
        currentState = MainState(this, Stack(), mutableListOf())
        for (token in tokens) {
            currentState.analyze(token)
        }

        val lastToken: Tokens = currentState.memory.peek()
        if (lastToken == Tokens.COLON && lastToken == Tokens.NEWLINE) {
            currentState.errorTokens.add(tokens.last())
        }

        return currentState.errorTokens
    }

    fun changeState(newState: State) {
        currentState = newState
    }
}