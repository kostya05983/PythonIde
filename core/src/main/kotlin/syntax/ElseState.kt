package syntax

import scanner.Token
import scanner.Tokens
import java.util.*

class ElseState(override val analyzer: SyntaxAnalizer, override val memory: Stack<Tokens>) : State {
    override fun analyze(token: Token) {
        when (token.token) {
            Tokens.COLON -> {
                analyzer.changeState(IndendState(analyzer, memory))
            }
            else -> {
                TODO("Error in Else State")
            }
        }
        memory.push(token.token)
    }
}