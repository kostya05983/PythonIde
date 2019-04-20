package scanner.ariphmeticScanAutomat

import scanner.Token
import java.util.*
import scanner.Alphabet
import scanner.Tokens

/**
 * from this state we decide it is continues of not or identifier
 * @author kostya05983
 */
class NOState(override val scanner: ArithmeticScanner,
              override val tokensArray: LinkedList<Token>,
              override val memory: Stack<Char>) : State {
    override fun parse(char: Char) {
        when (char) {
            Alphabet.T.ch -> {
                memory.push(char)
                scanner.changeState(NOTState(scanner, tokensArray, memory))
            }
            Alphabet.SPACE.ch -> {
                tokensArray.add(Token(Tokens.IDENTIFIER, memory.joinToString(separator = "")))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            else -> {
                memory.push(char)
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
        }
    }
}