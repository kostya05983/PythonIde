package syntax

import scanner.Token
import scanner.Tokens
import java.util.*

class IndentState(override val analyzer: SyntaxAnalyzer,
                  override val memory: Stack<Tokens>,
                  override val errorTokens: MutableList<Token>) : State {

    override fun analyze(token: Token) {
        when {
            token.token == Tokens.INDENT && memory.peek() == Tokens.NEWLINE -> {
                analyzer.changeState(MainState(analyzer, memory, errorTokens))
            }
            token.token == Tokens.NEWLINE -> {

            }
            else -> {
                errorTokens.add(token)
                analyzer.changeState(MainState(analyzer, memory, errorTokens))
            }
        }
        memory.push(token.token)
    }
}