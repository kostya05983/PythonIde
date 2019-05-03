package scanner.ifStates

import scanner.Token
import scanner.State
import java.util.*
import scanner.Alphabet
import scanner.ScannerAutomate

class ElState(override val scanner: ScannerAutomate,
              override val tokensArray: LinkedList<Token>,
              override val memory: Stack<Char>,
              override var offset: Int,
              override var page: Int) : State {
    override fun parse(char: Char) {
        memory.push(char)
        when (char) {
            Alphabet.L.ch -> {
                scanner.changeState(ElseElifState(scanner, tokensArray, memory, offset, page))
            }
            else -> {
                scanner.changeState(ConditionState(scanner, tokensArray, memory, offset, page))
            }
        }
    }
}