package scanner.ariphmeticScanAutomat

import scanner.Token
import java.util.*
import scanner.Alphabet
import scanner.Tokens

/**
 * if we meet space this is not
 * else this is identifier
 * @author kostya05983
 */
class NOTState(override val scanner: ArithmeticScanner,
               override val tokensArray: LinkedList<Token>,
               override val memory: Stack<Char>) : State {

    override fun parse(char: Char) {
        when (char) {
            Alphabet.SPACE.ch -> {
                memory.clear()
                tokensArray.add(Token(Tokens.NOT, Tokens.NOT.literal))
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            else -> {
                memory.push(char)
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
        }
    }
}