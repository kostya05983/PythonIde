package scanner.ariphmeticScanAutomat

import scanner.Token
import java.util.*

interface State {

    val scanner: ArithmeticScanner

    val tokensArray: LinkedList<Token>

    val memory: Stack<Char>

    /**
     * Parse character and select next state arithmetic's automat
     */
    fun parse(char: Char, currentLine: Int, offset: Int)
}