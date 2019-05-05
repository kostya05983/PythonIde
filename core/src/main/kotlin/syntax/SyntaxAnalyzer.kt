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

        if (lastToken == Tokens.COLON) {
            currentState.errorTokens.add(tokens.last())
        }

        //Second if it's start State of some production
        if (currentState is UnaryExpressionStartState ||
                currentState is BinaryExpressionState || currentState is ElseState) {
            currentState.errorTokens.add(tokens.last())
        }

        return currentState.errorTokens
    }

    fun changeState(newState: State) {
        currentState = newState
    }
}