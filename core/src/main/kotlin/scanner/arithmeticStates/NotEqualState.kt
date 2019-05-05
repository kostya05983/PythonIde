package scanner.arithmeticStates

import scanner.*
import java.util.*

/**
 * from this state we decide is it !=
 * or this identifier
 * @author kostya05983
 */
class NotEqualState(override val scanner: ScannerAutomate,
                    override val tokensArray: LinkedList<Token>,
                    override val memory: Stack<Char>,
                    override var offset: Int,
                    override var page: Int) : State {

    override fun parse(char: Char) {
        memory.push(char)
        when (char) {
            Alphabet.EQUAL.ch -> {
                tokensArray.add(Token(Tokens.NOT_EQUAL_C, memory, offset, page))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory, offset, page))
            }
            else -> {
                tokensArray.add(Token(Tokens.ASSIGNMENT, memory, offset, page))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory, offset, page))
            }
        }
    }
}