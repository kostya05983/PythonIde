package scanner.ariphmeticScanAutomat

import scanner.Token
import java.util.*
import scanner.Alphabet
import scanner.Tokens

/**
 * we decide that it is or or identifier
 * @author kostya05983
 */
class ORState(override val scanner: ArithmeticScanner,
              override val tokensArray: LinkedList<Token>,
              override val memory: Stack<Char>) : State {

    override fun parse(char: Char) {
        when (char) {
            Alphabet.SPACE.ch -> {
                memory.clear()
                tokensArray.add(Token(Tokens.OR, Tokens.OR.literal))
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            else -> {
                memory.push(char)
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
        }
    }
}