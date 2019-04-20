package scanner.ariphmeticScanAutomat

import java.util.*
import scanner.Alphabet
import scanner.Token

/**
 * From this state we decide this is just identifier an or we continue
 * to think that it's and
 * @author kostya05983
 */
class ANState(override val scanner: ArithmeticScanner,
              override val tokensArray: LinkedList<Token>,
              override val memory: Stack<Char>) : State {

    override fun parse(char: Char) {
        when (char) {
            Alphabet.D.ch -> {
                memory.push(char)
                scanner.changeState(ANDState(scanner, tokensArray, memory))
            }
            else -> {
                memory.push(char)
                TODO("todo clear stack and give a char")
            }
        }
    }
}