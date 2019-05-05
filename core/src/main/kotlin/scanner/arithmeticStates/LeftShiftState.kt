package scanner.arithmeticStates

import scanner.*
import java.util.*

/**
 *
 * If this is < we recognize this is left_shift
 * else it can be <= or just <ksdfjk
 * if we meet equal this is <= else we put new symbol in stack and think that
 * new symbol is start of identifier
 * @author kostya05983
 */
class LeftShiftState(override val scanner: ScannerAutomate,
                     override val tokensArray: LinkedList<Token>,
                     override val memory: Stack<Char>,
                     override var offset: Int,
                     override var page: Int) : State {

    override fun parse(char: Char) {

        when (char) {
            Alphabet.LEFT_SHIFT.ch -> {
                memory.push(char)
                tokensArray.add(Token(Tokens.SHIFT_LEFT, memory, offset, page))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory, offset, page))
            }
            Alphabet.EQUAL.ch -> {
                memory.push(char)
                tokensArray.add(Token(Tokens.LESS_EQUAL, memory, offset, page))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory, offset, page))
            }
            else -> {
                tokensArray.add(Token(Tokens.LESS, memory, offset, page))
                memory.clear()
                memory.push(char)
                scanner.changeState(MainState(scanner, tokensArray, memory, offset, page))
            }
        }
    }
}