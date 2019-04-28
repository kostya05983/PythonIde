package scanner.ariphmeticScanAutomat

import scanner.Token
import java.util.*
import scanner.Alphabet
import scanner.Tokens

class RightShiftState(override val scanner: ArithmeticScanner,
                      override val tokensArray: LinkedList<Token>,
                      override val memory: Stack<Char>) : State {
    override fun parse(char: Char, currentLine: Int, offset: Int) {
        memory.push(char)
        when (char) {
            Alphabet.RIGHT_SHIFT.ch -> {
                tokensArray.add(Token(Tokens.SHIFT_RIGHT, memory, offset, currentLine))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            Alphabet.EQUAL.ch -> {
                tokensArray.add(Token(Tokens.EQUAL, memory, offset, currentLine))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            else -> {
                tokensArray.add(Token(Tokens.MORE, memory, offset, currentLine))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
        }
    }
}