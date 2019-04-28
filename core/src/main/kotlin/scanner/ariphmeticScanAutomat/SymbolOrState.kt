package scanner.ariphmeticScanAutomat

import scanner.Token
import java.util.*
import scanner.Tokens
import scanner.Alphabet

class SymbolOrState(override val scanner: ArithmeticScanner,
                    override val tokensArray: LinkedList<Token>,
                    override val memory: Stack<Char>) : State {

    override fun parse(char: Char, currentLine: Int, offset: Int) {
        memory.push(char)
        when (char) {
            Alphabet.OR.ch -> {
                scanner.changeState(DoubleSymbolOrState(scanner, tokensArray, memory))
            }
            else -> {
                tokensArray.add(Token(Tokens.SLASH, Tokens.SLASH.literal))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
        }
    }
}