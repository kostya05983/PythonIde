package scanner.ifStates

import scanner.*
import java.util.*
import scanner.arithmeticStates.ArithmeticMainState

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
                scanner.changeState(ArithmeticMainState(scanner, tokensArray, memory, offset, page))
            }
            else -> {
                memory.push(char)
                scanner.changeState(ConditionState(scanner, tokensArray, memory, offset, page))
            }
        }
    }
}