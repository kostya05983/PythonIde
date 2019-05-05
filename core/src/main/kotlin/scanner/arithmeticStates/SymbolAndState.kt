package scanner.arithmeticStates

import scanner.*
import java.util.*

/**
 * from this state we decide this is double literal or not
 * @author kostya05983
 */
class SymbolAndState(override val scanner: ScannerAutomate,
                     override val tokensArray: LinkedList<Token>,
                     override val memory: Stack<Char>,
                     override var offset: Int,
                     override var page: Int) : State {

    override fun parse(char: Char) {
        when (char) {
            Alphabet.SPACE.ch -> {
                memory.push(char)
                tokensArray.add(Token(Tokens.BINARY_AND, memory, offset, page))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory, offset, page))
            }
            else -> {
                tokensArray.add(Token(Tokens.BINARY_AND, memory, offset, page))
                memory.clear()
                memory.push(char)
                scanner.changeState(MainState(scanner, tokensArray, memory, offset, page))
            }
        }
    }
}