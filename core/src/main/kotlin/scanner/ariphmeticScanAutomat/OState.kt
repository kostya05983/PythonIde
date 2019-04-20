package scanner.ariphmeticScanAutomat

import scanner.Token
import java.util.*
import scanner.Alphabet
import scanner.Tokens

/**
 * from this state we dicied this is continue of or or this is identifier
 * or continue of identifier
 * @author kostya05983
 */
class OState(override val scanner: ArithmeticScanner,
             override val tokensArray: LinkedList<Token>,
             override val memory: Stack<Char>) : State {
    override fun parse(char: Char) {
        when (char) {
            Alphabet.R.ch -> {
                memory.push(char)
                scanner.changeState(ORState(scanner, tokensArray, memory))
            }
            Alphabet.SPACE.ch -> {
                tokensArray.add(Token(Tokens.IDENTIFIER, memory.joinToString(separator = "")))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            else -> {
                memory.push(char)
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
        }
    }
}