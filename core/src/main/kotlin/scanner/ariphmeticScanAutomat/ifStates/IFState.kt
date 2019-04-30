package scanner.ariphmeticScanAutomat.ifStates

import scanner.Token
import scanner.ariphmeticScanAutomat.ArithmeticScanner
import scanner.ariphmeticScanAutomat.State
import java.util.*
import scanner.Alphabet
import scanner.Tokens
import scanner.ariphmeticScanAutomat.MainState

/**
 *  in else case maybe we have to go in endless statement state?
 * @author kostya05983
 */
class IFState(override val scanner: ArithmeticScanner,
              override val tokensArray: LinkedList<Token>,
              override val memory: Stack<Char>) : State {
    override fun parse(char: Char, currentLine: Int, offset: Int) {
        when (char) {
            Alphabet.SPACE.ch -> {
                tokensArray.add(Token(Tokens.IF, memory, offset, currentLine))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            else -> {
                scanner.changeState(ConditionState(scanner, tokensArray, memory))
            }
        }
    }
}