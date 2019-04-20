package scanner.ariphmeticScanAutomat

import java.util.*
import scanner.Alphabet
import scanner.Token


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
                TODO("add token, clear and go to new tokens")
            }
            else -> {
                TODO("IT is IDENTIFIER")
            }
        }
    }
}