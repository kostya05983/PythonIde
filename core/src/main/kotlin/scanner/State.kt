package scanner

import java.util.*

interface State {

    val scanner: ScannerAutomate

    val tokensArray: LinkedList<Token>

    val memory: Stack<Char>

    var offset: Int

    var page: Int

    /**
     * Parse character and select next state arithmetic's automat
     */
    fun parse(char: Char)
}