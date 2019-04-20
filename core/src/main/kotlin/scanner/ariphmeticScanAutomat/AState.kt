package scanner.ariphmeticScanAutomat

import java.util.*
import scanner.Alphabet
import scanner.Token

/**
 * From this state decide we think that it is and or identifier
 * @author kostya05983
 */
class AState(override val scanner: ArithmeticScanner,
             override val tokensArray: LinkedList<Token>,
             override val memory: Stack<Char>) : State {
    override fun parse(char: Char) {
        when (char) {
            Alphabet.N.ch -> {
                memory.push(char)
                scanner.changeState(ANState(scanner, tokensArray, memory))
            }
            else -> {
                memory.push(char)
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
        }
    }
}