package scanner.ariphmeticScanAutomat

import scanner.Token
import java.util.*
import scanner.Tokens
import scanner.Alphabet

class SymbolOrState(override val scanner: ArithmeticScanner,
                    override val tokensArray: LinkedList<Token>,
                    override val memory: Stack<Char>) : State {
    override fun parse(char: Char) {
        when (char) {
            Alphabet.OR.ch -> {
                memory.push(char)
                scanner.changeState(DoubleSymbolOrState(scanner, tokensArray, memory))
            }
            else -> {
                memory.clear()
                tokensArray.add(Token(Tokens.SLASH, Tokens.SLASH.literal))

                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
        }
    }
}