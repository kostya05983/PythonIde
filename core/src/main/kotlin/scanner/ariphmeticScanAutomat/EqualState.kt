package scanner.ariphmeticScanAutomat

import scanner.Token
import java.util.*
import scanner.Alphabet
import scanner.Tokens

class EqualState(override val scanner: ArithmeticScanner,
                 override val tokensArray: LinkedList<Token>,
                 override val memory: Stack<Char>) : State {

    override fun parse(char: Char, currentLine: Int, offset: Int) {
        memory.push(char)
        when (char) {
            Alphabet.EQUAL.ch -> {
                tokensArray.add(Token(Tokens.EQUAL, memory, offset, currentLine))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            else -> {
                tokensArray.add(Token(Tokens.ASSIGNMENT, memory, offset, currentLine))
                memory.clear()
                memory.push(char)
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
        }
    }
}