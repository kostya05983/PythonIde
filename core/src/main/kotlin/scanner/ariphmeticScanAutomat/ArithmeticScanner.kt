package scanner.ariphmeticScanAutomat

import scanner.Tokens
import java.util.*
import scanner.Token

/**
 * @author kostya05983
 * scanner for arithmetic expressions
 */
class ArithmeticScanner {

    var currentState: State = MainState(this, LinkedList(), Stack())

    fun scan(s: String): List<Token> {
        for (char in s) {
            currentState.parse(char)
        }
        if (currentState.memory.isNotEmpty())
            currentState.tokensArray.add(Token(Tokens.IDENTIFIER,
                    currentState.memory.joinToString("")))

        return currentState.tokensArray.filter {
            it.value != ""
        }
    }

    fun changeState(newState: State) {
        currentState = newState
    }

    fun joinToIdentifier(memory: Stack<Char>): Token {
        val sb = StringBuilder()
        for (ch in memory) {
            if (ch != ' ')
                sb.append(ch)
        }
        return Token(Tokens.IDENTIFIER, sb.toString())
    }
}