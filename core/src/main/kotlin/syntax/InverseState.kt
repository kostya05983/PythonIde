package syntax

import scanner.Token
import scanner.Tokens
import java.util.*

class InverseState(override val analyzer: SyntaxAnalizer,
                   override val memory: Stack<Tokens>) : State {

    override fun analyze(token: Token) {
        when (token.token) {
            Tokens.INVERSE -> {
            }
            Tokens.IDENTIFIER -> {
                analyzer.changeState(BinaryExpressionState(analyzer, memory))
            }
            Tokens.TRUE -> {
                analyzer.changeState(BinaryExpressionState(analyzer, memory))
            }
            Tokens.FALSE -> {
                analyzer.changeState(BinaryExpressionState(analyzer, memory))
            }
            else -> {
                TODO("Error in inverse state")
            }
        }
        memory.push(token.token)
    }
}