package scanner.ariphmeticScanAutomat

import java.util.*

/**
 * @author kostya05983
 * scanner for arithmetic expressions
 */
class ArithmeticScanner {

    var currenState: State = MainState(this, LinkedList(), Stack())

    fun scan(s: String) {
        for (char in s) {
            currenState.parse(char)
        }
    }

    fun changeState(newState: State) {
        currenState = newState
    }
}