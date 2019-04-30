package scanner.ariphmeticScanAutomat.ifStates

import scanner.Token
import scanner.ariphmeticScanAutomat.ArithmeticScanner
import scanner.ariphmeticScanAutomat.State
import java.util.*
import scanner.Alphabet

class ConditionState(override val scanner: ArithmeticScanner,
                     override val tokensArray: LinkedList<Token>,
                     override val memory: Stack<Char>) : State {
    override fun parse(char: Char, currentLine: Int, offset: Int) {
        when {
            memory.peek() == null && char == Alphabet.I.ch -> {
                memory.push(char)
                scanner.changeState(IState(scanner, tokensArray, memory))
            }
            memory.peek() == null && Alphabet.E.ch == char -> {
                memory.push(char)
                scanner.changeState(ElState(scanner, tokensArray, memory))
            }
            else -> {
                memory.push(char)
            }
        }
    }
}