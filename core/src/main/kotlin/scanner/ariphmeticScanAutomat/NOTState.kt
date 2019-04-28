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

    override fun parse(char: Char, currentLine: Int, offset: Int) {
        memory.push(char)
        when (char) {
            Alphabet.SPACE.ch -> {
                tokensArray.add(Token(Tokens.NOT, memory, offset, currentLine))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            else -> {
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
        }
    }
}