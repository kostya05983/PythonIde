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

    override fun parse(char: Char) {
        when (char) {
            Alphabet.DIVIDER.ch -> {
                memory.clear()
                tokensArray.add(Token(Tokens.FLOOR_DIVISION, Tokens.FLOOR_DIVISION.literal))
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            else -> {
                TODO("This is error")
            }
        }
    }
}