package scanner.ariphmeticScanAutomat

import scanner.Token
import java.util.*
import scanner.Alphabet
import scanner.Tokens

class OState(override val scanner: ArithmeticScanner,
             override val tokensArray: LinkedList<Token>,
             override val memory: Stack<Char>) : State {
    override fun parse(char: Char) {
        when(char) {
            Alphabet.R.ch -> {
                memory.push(char)
                scanner.changeState(ORState(scanner, tokensArray, memory))
            }
            else -> {

            }
        }
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}