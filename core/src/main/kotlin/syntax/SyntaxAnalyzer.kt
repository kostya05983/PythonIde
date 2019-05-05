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

        if (currentState.memory.isNotEmpty()) {
            val lastToken: Tokens = peekIgnoringNewLine()

            if (lastToken == Tokens.COLON) {
                currentState.errorTokens.add(tokens.last())
            }
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

    fun peekIgnoringNewLine(): Tokens {
        val l = currentState.memory.toList()
        val result: Tokens = currentState.memory.peek()

        for (i in l.size - 1 downTo 0) {
            if (l[i] != Tokens.NEWLINE) {
                return l[i]
            }
        }

        return result
    }


}