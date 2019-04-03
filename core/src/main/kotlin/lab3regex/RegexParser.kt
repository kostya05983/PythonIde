package lab3regex

import lab2automat.OutputStrategy
import lab3regex.automat.StartState
import lab3regex.automat.State
import java.util.regex.Pattern

class RegexParser(private val outputStrategy: OutputStrategy) {
    var currentState: State = StartState(this, outputStrategy)

    private val pattern = Pattern.compile("(.doc|.yaml|.pdf)")

    fun parse(s: String) {
        val matcher = pattern.matcher(s)
        while (matcher.find()) {
            outputStrategy.println(matcher.group(1))
        }
    }

    fun changeState(new: State) {
        currentState = new
    }
}