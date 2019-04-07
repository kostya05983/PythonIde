package lab3regex

import lab2automat.OutputStrategy
import lab3regex.automat.StartState
import lab3regex.automat.State
import java.nio.Buffer
import java.util.regex.Pattern

/**
 * @author kostya05983
 * Parser of regex
 */
class RegexParser(outputStrategy: OutputStrategy) {
    var currentState: State = StartState(this, outputStrategy)

    fun parse(s: String) {
        val sb = StringBuilder()
        for (ch in s) {
            currentState.output(ch, sb)
        }
        currentState.output('\\', sb)
    }

    fun changeState(new: State) {
        currentState = new
    }
}