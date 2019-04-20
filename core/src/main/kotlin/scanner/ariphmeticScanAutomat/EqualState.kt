package scanner.ariphmeticScanAutomat

import scanner.Token
import java.util.*
import scanner.Alphabet
import scanner.Tokens

class EqualState(override val scanner: ArithmeticScanner,
                 override val tokensArray: LinkedList<Token>,
                 override val memory: Stack<Char>) : State {
    override fun parse(char: Char) {
        when (char) {
            Alphabet.EQUAL.ch -> {
                memory.clear()
                tokensArray.add(Token(Tokens.EQUAL, Tokens.EQUAL.literal))
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            else -> {
                memory.push(char)
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
        }
    }
}