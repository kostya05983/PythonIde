package syntax

import scanner.Token
import scanner.Tokens
import java.util.*

class PlusState(override val analyzer: SyntaxAnalyzer,
                override val memory: Stack<Tokens>) : State {
    override fun analyze(token: Token) {
        when (token.token) {
            Tokens.PLUS -> {
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
                TODO("Error in plus state")
            }
        }
        memory.push(token.token)
    }
}