package scanner.ariphmeticScanAutomat

import scanner.Token
import java.util.*

class RightShiftState(override val scanner: ArithmeticScanner,
                      override val tokensArray: LinkedList<Token>,
                      override val memory: Stack<Char>) : State {
    override fun parse(char: Char) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}