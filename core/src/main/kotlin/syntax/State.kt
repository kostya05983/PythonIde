package syntax

import scanner.Token
import scanner.Tokens
import java.util.*

interface State {
    val analyzer: SyntaxAnalizer

    val memory: Stack<Tokens>

    fun analyze(token: Token)
}