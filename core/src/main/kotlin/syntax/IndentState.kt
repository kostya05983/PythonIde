package syntax

import scanner.Token
import scanner.Tokens
import java.util.*

class IndentState(override val analyzer: SyntaxAnalyzer,
                  override val memory: Stack<Tokens>,
                  override val errorTokens: List<Token>) : State {

    override fun analyze(token: Token) {
        when {
            token.token == Tokens.INDENT && memory.peek() == Tokens.NEWLINE -> {
                analyzer.changeState(MainState(analyzer, memory, errorTokens))
            }
            token.token == Tokens.NEWLINE -> {

            }
            else -> {
                TODO("Error in INTENT state")
            }
        }
        memory.push(token.token)
    }
}