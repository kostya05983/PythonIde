package scanner.arithmeticStates

import scanner.*
import java.util.*

/**
 * if we meet space this is not
 * else this is identifier
 * @author kostya05983
 */
class NOTState(override val scanner: ScannerAutomate,
               override val tokensArray: LinkedList<Token>,
               override val memory: Stack<Char>,
               override var offset: Int,
               override var page: Int) : State {

    override fun parse(char: Char) {
        memory.push(char)
        when (char) {
            Alphabet.SPACE.ch -> {
                tokensArray.add(Token(Tokens.NOT, memory, offset, page))
                memory.clear()
                scanner.changeState(ArithmeticMainState(scanner, tokensArray, memory, offset, page))
            }
            else -> {
                scanner.changeState(ArithmeticMainState(scanner, tokensArray, memory, offset, page))
            }
        }
    }
}