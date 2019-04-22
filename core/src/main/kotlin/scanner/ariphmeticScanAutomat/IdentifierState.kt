package scanner.ariphmeticScanAutomat

import scanner.Token
import java.util.*
import scanner.Alphabet
import scanner.Tokens

/**
 * From this state we read identifier
 * @author kostya05983
 */
class IdentifierState(override val scanner: ArithmeticScanner,
                      override val tokensArray: LinkedList<Token>,
                      override val memory: Stack<Char>) : State {

    override fun parse(char: Char) {
        when (char) {
            Alphabet.SPACE.ch -> {
                tokensArray.add(Token(Tokens.IDENTIFIER, memory.joinToString(separator = "")))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            else -> {
                memory.push(char)
            }
        }
    }
}