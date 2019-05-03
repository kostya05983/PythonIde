package scanner.ifStates

import scanner.*
import java.util.*

class ElseState(override val scanner: ScannerAutomate,
                override val tokensArray: LinkedList<Token>,
                override val memory: Stack<Char>,
                override var offset: Int,
                override var page: Int) : State {

    override fun parse(char: Char) {
        memory.push(char)
        when (char) {
            Alphabet.E.ch -> {
                tokensArray.add(Token(Tokens.ELSE, memory, offset, page))
                memory.clear()
                scanner.changeState(ConditionState(scanner, tokensArray, memory, offset, page))
            }
            else -> {
                scanner.changeState(ConditionState(scanner, tokensArray, memory, offset, page))
            }
        }
    }
}