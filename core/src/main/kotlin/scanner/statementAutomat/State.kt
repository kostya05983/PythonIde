package scanner.statementAutomat

import scanner.Token
import java.util.*

interface State {
    val scanner: StatementScanner
    val tokenArray: LinkedList<Token>
    val memory: Stack<Char>

    fun scan(char: Char)
}