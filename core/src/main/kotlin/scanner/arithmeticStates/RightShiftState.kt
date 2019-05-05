package scanner.arithmeticStates

import scanner.*
import java.util.*

class RightShiftState(override val scanner: ScannerAutomate,
                      override val tokensArray: LinkedList<Token>,
                      override val memory: Stack<Char>,
                      override var offset: Int,
                      override var page: Int) : State {
    override fun parse(char: Char) {
        memory.push(char)
        when (char) {
            Alphabet.RIGHT_SHIFT.ch -> {
                tokensArray.add(Token(Tokens.SHIFT_RIGHT, memory, offset, page))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory, offset, page))
            }
            Alphabet.EQUAL.ch -> {
                tokensArray.add(Token(Tokens.EQUAL, memory, offset, page))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory, offset, page))
            }
            else -> {
                tokensArray.add(Token(Tokens.MORE, memory, offset, page))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory, offset, page))
            }
        }
    }
}