package scanner.ariphmeticScanAutomat

import scanner.Token
import java.util.*
import scanner.Alphabet
import scanner.Tokens

/**
 * From this state we decide this char the coninue of no, or this identifier
 * or continue of identifier
 * @author kostya05983
 */
class NState(override val scanner: ArithmeticScanner,
             override val tokensArray: LinkedList<Token>,
             override val memory: Stack<Char>) : State {
    override fun parse(char: Char) {
        when (char) {
            Alphabet.O.ch -> {
                memory.push(char)
                scanner.changeState(NOState(scanner, tokensArray, memory))
            }
            Alphabet.SPACE.ch -> {
                memory.push(char)
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