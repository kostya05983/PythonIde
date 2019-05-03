package scanner.arithmeticStates

import scanner.*
import java.util.*

/**
 * in this state we decide it floor division or not floor division
 * @author kosta05983
 */
class DividerState(override val scanner: ScannerAutomate,
                   override val tokensArray: LinkedList<Token>,
                   override val memory: Stack<Char>,
                   override var offset: Int,
                   override var page: Int) : State {

    override fun parse(char: Char) {
        memory.push(char)
        when (char) {
            Alphabet.DIVIDER.ch -> {
                tokensArray.add(Token(Tokens.FLOOR_DIVISION, memory, offset, page))
                memory.clear()
                scanner.changeState(ArithmeticMainState(scanner, tokensArray, memory, offset, page))
            }
            else -> {
                tokensArray.add(Token(Tokens.DIVIDE, memory, offset, page))
                memory.clear()
                scanner.changeState(ArithmeticMainState(scanner, tokensArray, memory, offset, page))
            }
        }
    }
}