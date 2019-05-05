package scanner.arithmeticStates

import scanner.*
import java.util.*

class SymbolOrState(override val scanner: ScannerAutomate,
                    override val tokensArray: LinkedList<Token>,
                    override val memory: Stack<Char>,
                    override var offset: Int,
                    override var page: Int) : State {

    override fun parse(char: Char) {
        memory.push(char)
        when (char) {
            Alphabet.SPACE.ch -> {
                tokensArray.add(Token(Tokens.SLASH, memory, offset, page))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory, offset, page))
            }
            else -> {
                tokensArray.add(Token(Tokens.UNRECOGNIZED, memory, offset, page))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory, offset, page))
            }
        }
    }
}