package scanner.ifStates

import scanner.*
import java.util.*
import scanner.MainState

class ElifState(override val scanner: ScannerAutomate,
                override val tokensArray: LinkedList<Token>,
                override val memory: Stack<Char>,
                override var offset: Int,
                override var page: Int) : State {

    override fun parse(char: Char) {
        when (char) {
            Alphabet.F.ch -> {
                memory.push(char)
                tokensArray.add(Token(Tokens.ELIF, memory, offset, page))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory, offset, page))
            }
            else -> {
                memory.push(char)
                scanner.changeState(MainState(scanner, tokensArray, memory, offset, page))
            }
        }
    }
}