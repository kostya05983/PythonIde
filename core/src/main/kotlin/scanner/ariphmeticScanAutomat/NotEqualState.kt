package scanner.ariphmeticScanAutomat

import scanner.Token
import java.util.*
import scanner.Alphabet
import scanner.Tokens

/**
 * from this state we decide is it !=
 * or this identifier
 * @author kostya05983
 */
class NotEqualState(override val scanner: ArithmeticScanner,
                    override val tokensArray: LinkedList<Token>,
                    override val memory: Stack<Char>) : State {
    override fun parse(char: Char) {
        when (char) {
            Alphabet.EQUAL.ch -> {
                memory.clear()
                tokensArray.add(Token(Tokens.NOT_EQUAL_C, Tokens.NOT_EQUAL_C.literal))
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            else -> {
                //todo error
//                memory.push(char)
//                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
        }
    }
}