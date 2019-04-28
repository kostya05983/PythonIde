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

    fun scan(s: String, currentLine: Int, offset: Int): List<Token> {
        for (char in s) {
            currentState.parse(char, currentLine, offset)
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

    fun joinToIdentifier(memory: Stack<Char>, offset: Int, paragraph: Int): Token {
        val sb = StringBuilder()

        var c = false
        for (ch in memory) {
            sb.append(ch)
            if (ch != ' ')
                c = true
        }

        return if (c) {
            val token = Token(Tokens.IDENTIFIER, memory, offset, paragraph)
            memory.clear()
            token
        } else {
            Token(Tokens.EMPTY_TOKEN, "")
        }
    }
}