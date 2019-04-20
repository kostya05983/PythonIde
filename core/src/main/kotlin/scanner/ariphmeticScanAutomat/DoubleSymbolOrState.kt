package scanner.ariphmeticScanAutomat

import scanner.Token
import java.util.*
import scanner.Alphabet
import scanner.Tokens

class DoubleSymbolOrState(override val scanner: ArithmeticScanner,
                          override val tokensArray: LinkedList<Token>,
                          override val memory: Stack<Char>) : State {
    override fun parse(char: Char) {
        when (char) {
            Alphabet.SPACE.ch -> {
                memory.clear()
                tokensArray.add(Token(Tokens.SLASH_DOUBLE, Tokens.SLASH_DOUBLE.literal))
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            else -> {
                TODO("error")
            }
        }
    }
}