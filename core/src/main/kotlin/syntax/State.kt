package syntax

import scanner.Token
import scanner.Tokens
import java.util.*

interface State {
    val analyzer: SyntaxAnalyzer

    val memory: Stack<Tokens>

    val errorTokens: List<Token>

    fun analyze(token: Token)
}