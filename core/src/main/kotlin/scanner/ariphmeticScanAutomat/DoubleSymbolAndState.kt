package scanner.ariphmeticScanAutomat

import scanner.Token
import java.util.*
import scanner.Alphabet
import scanner.Tokens

/**
 * @author kostya05983
 */
class DoubleSymbolAndState(override val scanner: ArithmeticScanner,
                           override val tokensArray: LinkedList<Token>,
                           override val memory: Stack<Char>) : State {
    override fun parse(char: Char) {
        when (char) {
            Alphabet.SPACE.ch -> {
                memory.clear()
                tokensArray.add(Token(Tokens.AND_DOUBLE, Tokens.AND_DOUBLE.literal))
            }
            else -> {
                TODO("error")
            }
        }
    }
}