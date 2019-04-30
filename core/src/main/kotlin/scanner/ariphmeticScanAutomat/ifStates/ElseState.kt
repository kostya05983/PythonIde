package scanner.ariphmeticScanAutomat.ifStates

import scanner.Token
import scanner.ariphmeticScanAutomat.ArithmeticScanner
import scanner.ariphmeticScanAutomat.State
import java.util.*
import scanner.Alphabet
import scanner.Tokens

class ElseState(override val scanner: ArithmeticScanner,
                override val tokensArray: LinkedList<Token>,
                override val memory: Stack<Char>) : State {
    override fun parse(char: Char, currentLine: Int, offset: Int) {
        when (char) {
            Alphabet.E.ch -> {
                tokensArray.add(Token(Tokens.ELSE, memory, offset, currentLine))
                memory.clear()
                scanner.changeState(ConditionState(scanner, tokensArray, memory))
            }
            else -> {
                //TODO go to statement State
                scanner.changeState(ConditionState(scanner, tokensArray, memory))
            }
        }
    }
}