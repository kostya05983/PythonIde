package syntax

import scanner.Token
import scanner.Tokens
import java.util.*

/**
 *
 * @author kostya05983
 */
class MainState(override val analyzer: SyntaxAnalizer,
                override val memory: Stack<Tokens>) : State {


    override fun analyze(token: Token) {
        when (token.token) {
            Tokens.NEWLINE -> {
            }
            Tokens.SIMPLE_STMT -> {
            }
            Tokens.IF -> {
                analyzer.changeState(UnaryExpressionStartState(analyzer, memory))
            }
            Tokens.ELIF -> {
                analyzer.changeState(UnaryExpressionStartState(analyzer, memory))
            }
            Tokens.ELSE -> {
                analyzer.changeState(ElseState(analyzer, memory))
            }
            else -> {
                TODO("We can't start with this")
            }
        }
        memory.push(token.token)
    }
}