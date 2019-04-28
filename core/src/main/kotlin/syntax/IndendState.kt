package syntax

import scanner.Token
import scanner.Tokens
import java.util.*

class IndendState(override val analyzer: SyntaxAnalizer,
                  override val memory: Stack<Tokens>) : State {

    override fun analyze(token: Token) {
        when (token.token) {
            Tokens.INDENT -> {
                analyzer.changeState(MainState(analyzer, memory))
            }
            else -> {
                TODO("Error in INTENT state")
            }
        }
        memory.push(token.token)
    }
}