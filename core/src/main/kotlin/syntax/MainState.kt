package syntax

import scanner.Token
import scanner.Tokens
import java.util.*

/**
 *
 * @author kostya05983
 */
class MainState(override val analyzer: SyntaxAnalyzer,
                override val memory: Stack<Tokens>,
                override val errorTokens: List<Token>) : State {


    override fun analyze(token: Token) {
        when (token.token) {
            Tokens.NEWLINE -> {
            }
            Tokens.SIMPLE_STMT -> {
            }
            Tokens.IF -> {
                analyzer.changeState(UnaryExpressionStartState(analyzer, memory, errorTokens))
            }
            Tokens.ELIF -> {
                analyzer.changeState(UnaryExpressionStartState(analyzer, memory, errorTokens))
            }
            Tokens.ELSE -> {
                analyzer.changeState(ElseState(analyzer, memory, errorTokens))
            }
            else -> {
                TODO("We can't start with this")
            }
        }
        memory.push(token.token)
    }
}