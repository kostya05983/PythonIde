package scanner.ariphmeticScanAutomat

import scanner.Token
import java.util.*
import scanner.Alphabet
import scanner.Tokens

class RightShiftState(override val scanner: ArithmeticScanner,
                      override val tokensArray: LinkedList<Token>,
                      override val memory: Stack<Char>) : State {
    override fun parse(char: Char) {
        when (char) {
            Alphabet.RIGHT_SHIFT.ch -> {
                memory.clear()
                tokensArray.add(Token(Tokens.SHIFT_RIGHT, Tokens.SHIFT_RIGHT.literal))
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            Alphabet.EQUAL.ch -> {
                memory.clear()
                tokensArray.add(Token(Tokens.EQUAL, Tokens.EQUAL.literal))
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            else -> {
                memory.clear()
                tokensArray.add(Token(Tokens.MORE, Tokens.MORE.literal))

                memory.push(char)
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
        }
    }
}