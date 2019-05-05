package scanner.arithmeticStates

import scanner.*
import java.util.*
import scanner.MainState


/**
 * We dicide what it is identifier or not in this state
 * if we meet space it's and if not it's identifier
 * @author kostya05983
 */
class ANDState(override val scanner: ScannerAutomate,
               override val tokensArray: LinkedList<Token>,
               override val memory: Stack<Char>,
               override var offset: Int,
               override var page: Int) : State {

    override fun parse(char: Char) {
        memory.push(char)
        when (char) {
            Alphabet.SPACE.ch -> {
                tokensArray.add(Token(Tokens.AND, memory, offset, page))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory, offset, page))
            }
            else -> {
                scanner.changeState(MainState(scanner, tokensArray, memory, offset, page))
            }
        }
    }
}