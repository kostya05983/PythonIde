package scanner.statementAutomat

import scanner.Token
import java.util.*
import scanner.Alphabet
import scanner.Tokens

/**
 * State for analyze statements
 * @author kostya05983
 */
class MainState(override val tokenArray: LinkedList<Token>,
                override val memory: Stack<Char>,
                override val scanner: StatementScanner) : State {

    override fun scan(char: Char) {
        when (char) {
            Alphabet.EQUAL.ch -> {
                addIfNeed()
                tokenArray.add(Token(Tokens.ASSIGNMENT, Tokens.ASSIGNMENT.literal))
            }
            Alphabet.LEFT_BRACKET.ch -> {
                addIfNeed()
                tokenArray.add(Token(Tokens.LEFT_BRACKET, Tokens.LEFT_BRACKET.literal))
                scanner.changeState(SimpleStatementState(scanner, tokenArray, memory))
            }
            else -> {
                memory.push(char)
            }
        }
    }

    /**
     * Add identifier if stack is not empty
     */
    private fun addIfNeed() {
        if (memory.isNotEmpty()) {
            tokenArray.add(Token(Tokens.IDENTIFIER, memory.joinToString("")))
            memory.clear()
        }
    }
}