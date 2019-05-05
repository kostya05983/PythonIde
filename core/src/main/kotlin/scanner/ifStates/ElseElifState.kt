package scanner.ifStates

import scanner.Token
import scanner.State
import java.util.*
import scanner.Alphabet
import scanner.ScannerAutomate
import scanner.MainState

class ElseElifState(override val scanner: ScannerAutomate,
                    override val tokensArray: LinkedList<Token>,
                    override val memory: Stack<Char>,
                    override var offset: Int,
                    override var page: Int) : State {
    override fun parse(char: Char) {
        memory.push(char)
        when (char) {
            Alphabet.S.ch -> {
                scanner.changeState(ElseState(scanner, tokensArray, memory, offset, page))
            }
            Alphabet.I.ch -> {
                scanner.changeState(ElifState(scanner, tokensArray, memory, offset, page))
            }
            else -> {
                scanner.changeState(MainState(scanner, tokensArray, memory, offset, page))
            }
        }
    }
}