package scanner.ariphmeticScanAutomat

import java.util.*
import scanner.Alphabet
import scanner.Token
import scanner.Tokens


/**
 * We dicide what it is identifier or not in this state
 * if we meet space it's and if not it's identifier
 * @author kostya05983
 */
class ANDState(override val scanner: ArithmeticScanner,
               override val tokensArray: LinkedList<Token>,
               override val memory: Stack<Char>) : State {

    override fun parse(char: Char) {
        when (char) {
            Alphabet.SPACE.ch -> {
                tokensArray.add(Token(Tokens.AND, Tokens.AND.literal))
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