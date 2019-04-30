package scanner.ariphmeticScanAutomat

import scanner.Token
import java.util.*
import scanner.Alphabet
import scanner.Tokens

/**
 * from this state we decide is it !=
 * or this identifier
 * @author kostya05983
 */
class NotEqualState(override val scanner: ArithmeticScanner,
                    override val tokensArray: LinkedList<Token>,
                    override val memory: Stack<Char>) : State {

    override fun parse(char: Char, currentLine: Int, offset: Int) {
        memory.push(char)
        when (char) {
            Alphabet.EQUAL.ch -> {
                tokensArray.add(Token(Tokens.NOT_EQUAL_C, memory, offset, currentLine))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            else -> {
                tokensArray.add(Token(Tokens.ASSIGNMENT, memory, offset, currentLine))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
        }
    }
}