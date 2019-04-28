package scanner.ariphmeticScanAutomat

import scanner.Token
import java.util.*
import scanner.Alphabet
import scanner.Tokens

/**
 *
 * If this is < we recognize this is left_shift
 * else it can be <= or just <ksdfjk
 * if we meet equal this is <= else we put new symbol in stack and think that
 * new symbol is start of identifier
 * @author kostya05983
 */
class LeftShiftState(override val scanner: ArithmeticScanner,
                     override val tokensArray: LinkedList<Token>,
                     override val memory: Stack<Char>) : State {

    override fun parse(char: Char, currentLine: Int, offset: Int) {
        memory.push(char)
        when (char) {
            Alphabet.LEFT_SHIFT.ch -> {
                tokensArray.add(Token(Tokens.SHIFT_LEFT, memory, offset, currentLine))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            Alphabet.EQUAL.ch -> {
                tokensArray.add(Token(Tokens.LESS_EQUAL, memory, offset, currentLine))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            else -> {
                tokensArray.add(Token(Tokens.LESS, memory, offset, currentLine))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
        }
    }
}