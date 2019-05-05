package scanner.arithmeticStates

import scanner.*
import java.util.*

/**
 * we decide that it is or or identifier
 * @author kostya05983
 */
class ORState(override val scanner: ScannerAutomate,
              override val tokensArray: LinkedList<Token>,
              override val memory: Stack<Char>,
              override var offset: Int,
              override var page: Int) : State {

    override fun parse(char: Char) {
        memory.push(char)
        when (char) {
            Alphabet.SPACE.ch -> {
                tokensArray.add(Token(Tokens.OR, memory, offset, page))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory, offset, page))
            }
            else -> {
                scanner.changeState(MainState(scanner, tokensArray, memory, offset, page))
            }
        }
    }
}