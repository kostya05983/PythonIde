package scanner.statementAutomat

import scanner.Token
import java.util.*
import scanner.Alphabet
import scanner.Tokens

class SimpleStatementState(override val scanner: StatementScanner,
                           override val tokenArray: LinkedList<Token>,
                           override val memory: Stack<Char>) : State {
    override fun scan(char: Char) {
        when (char) {
            Alphabet.RIGHT_BRACKET.ch -> {
                tokenArray.add(Token(Tokens.IDENTIFIER, memory.joinToString("")))
                memory.clear()
                tokenArray.add(Token(Tokens.RIGHT_BRACKET, Tokens.RIGHT_BRACKET.literal))
                scanner.changeState(MainState(tokenArray, memory, scanner))
            }
            else -> {
                memory.push(char)
            }
        }
    }
}