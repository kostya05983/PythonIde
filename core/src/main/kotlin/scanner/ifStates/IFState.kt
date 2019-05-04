package scanner.ifStates

import scanner.*
import java.util.*
import scanner.arithmeticStates.ArithmeticMainState

/**
 *  in else case maybe we have to go in endless statement state?
 * @author kostya05983
 */
class IFState(override val scanner: ScannerAutomate,
              override val tokensArray: LinkedList<Token>,
              override val memory: Stack<Char>,
              override var offset: Int,
              override var page: Int) : State {
    override fun parse(char: Char) {
        when (char) {
            Alphabet.SPACE.ch -> {
                memory.push(char)
                tokensArray.add(Token(Tokens.IF, memory, offset, page))
                memory.clear()
                scanner.changeState(ArithmeticMainState(scanner, tokensArray, memory, offset, page))
            }
            else -> {
                scanner.changeState(ConditionState(scanner, tokensArray, memory, offset, page))
            }
        }
    }
}