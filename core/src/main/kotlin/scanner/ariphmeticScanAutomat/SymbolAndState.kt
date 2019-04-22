package scanner.ariphmeticScanAutomat

import scanner.Token
import java.util.*
import scanner.Alphabet
import scanner.Tokens

/**
 * from this state we decide this is double literal or not
 * @author kostya05983
 */
class SymbolAndState(override val scanner: ArithmeticScanner,
                     override val tokensArray: LinkedList<Token>,
                     override val memory: Stack<Char>) : State {

    override fun parse(char: Char) {
        when (char) {
            Alphabet.AND.ch -> {
                memory.push(char)
                scanner.changeState(DoubleSymbolAndState(scanner, tokensArray, memory))
            }
            else -> {
                memory.clear()
                memory.push(char)
                tokensArray.add(Token(Tokens.BINARY_AND, Tokens.BINARY_AND.literal))
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
        }
    }
}