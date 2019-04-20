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

    override fun parse(char: Char) {
        when (char) {
            Alphabet.LEFT_SHIFT.ch -> {
                memory.clear()
                tokensArray.add(Token(Tokens.SHIFT_LEFT, Tokens.SHIFT_LEFT.literal))
            }
            Alphabet.EQUAL.ch -> {
                memory.clear()
                tokensArray.add(Token(Tokens.EQUAL, Tokens.EQUAL.literal))
            }
            else -> {
                memory.clear()
                tokensArray.add(Token(Tokens.LESS, Tokens.LESS.literal))
                memory.push(char)
            }
        }
    }


}