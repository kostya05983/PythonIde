package syntax

import scanner.Token
import scanner.Tokens
import java.util.*


class NotState(override val analyzer: SyntaxAnalyzer,
               override val memory: Stack<Tokens>,
               override val errorTokens: List<Token>) : State {
    override fun analyze(token: Token) {
        when (token.token) {
            Tokens.NOT -> {
            }
            Tokens.IDENTIFIER -> {
                analyzer.changeState(BinaryExpressionState(analyzer, memory, errorTokens))
            }
            Tokens.TRUE -> {
                analyzer.changeState(BinaryExpressionState(analyzer, memory, errorTokens))
            }
            Tokens.FALSE -> {
                analyzer.changeState(BinaryExpressionState(analyzer, memory, errorTokens))
            }
            else -> {
                TODO("Error not state")
            }
        }
        memory.push(token.token)
    }
}