package syntax

import scanner.Token

interface State {
    val analyzer: SyntaxAnalizer

    fun analyze(token: Token)
}