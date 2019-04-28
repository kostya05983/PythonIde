package syntax

import scanner.Token
import scanner.Tokens
import java.util.*

class InverseState(override val analyzer: SyntaxAnalyzer,
                   override val memory: Stack<Tokens>,
                   override val errorTokens: MutableList<Token>) : State {

    override fun analyze(token: Token) {
        when (token.token) {
            Tokens.INVERSE -> {
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
                errorTokens.add(token)
            }
        }
        memory.push(token.token)
    }
}