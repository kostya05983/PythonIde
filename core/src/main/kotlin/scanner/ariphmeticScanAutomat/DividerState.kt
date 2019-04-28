package scanner.ariphmeticScanAutomat

import scanner.Token
import java.util.*
import scanner.Tokens
import scanner.Alphabet

/**
 * in this state we decide it floor division or not floor division
 * @author kosta05983
 */
class DividerState(override val scanner: ArithmeticScanner,
                   override val tokensArray: LinkedList<Token>,
                   override val memory: Stack<Char>) : State {

    override fun parse(char: Char, currentLine: Int, offset: Int) {
        memory.push(char)
        when (char) {
            Alphabet.DIVIDER.ch -> {
                tokensArray.add(Token(Tokens.FLOOR_DIVISION, memory, offset, currentLine))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            else -> {
                tokensArray.add(Token(Tokens.DIVIDE, memory, offset, currentLine))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
        }
    }
}