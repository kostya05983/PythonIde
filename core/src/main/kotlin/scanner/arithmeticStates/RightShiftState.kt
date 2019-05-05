package scanner.arithmeticStates

import scanner.*
import java.util.*

class RightShiftState(override val scanner: ScannerAutomate,
                      override val tokensArray: LinkedList<Token>,
                      override val memory: Stack<Char>,
                      override var offset: Int,
                      override var page: Int) : State {
    override fun parse(char: Char) {
        when (char) {
            Alphabet.RIGHT_SHIFT.ch -> {
                memory.push(char)
                tokensArray.add(Token(Tokens.SHIFT_RIGHT, memory, offset, page))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory, offset, page))
            }
            Alphabet.EQUAL.ch -> {
                memory.push(char)
                tokensArray.add(Token(Tokens.MORE_EQUAL, memory, offset, page))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory, offset, page))
            }
            else -> {
                tokensArray.add(Token(Tokens.MORE, memory, offset, page))
                memory.clear()
                memory.push(char)
                scanner.changeState(MainState(scanner, tokensArray, memory, offset, page))
            }
        }
    }
}