package scanner.ariphmeticScanAutomat.ifStates

import scanner.Token
import scanner.ariphmeticScanAutomat.ArithmeticScanner
import scanner.ariphmeticScanAutomat.State
import java.util.*
import scanner.Alphabet

class ElState(override val scanner: ArithmeticScanner,
              override val tokensArray: LinkedList<Token>,
              override val memory: Stack<Char>) : State {
    override fun parse(char: Char, currentLine: Int, offset: Int) {
        memory.push(char)
        when (char) {
            Alphabet.L.ch -> {
                scanner.changeState(ElseElifState(scanner, tokensArray, memory))
            }
            else -> {
                scanner.changeState(ConditionState(scanner, tokensArray, memory))
            }
        }
    }
}