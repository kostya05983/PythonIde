package lab3regex.automat

import lab2automat.OutputStrategy
import lab3regex.RegexParser

/**
 * @author kostya05983
 * Describe the start of automat for third lab
 */
interface State {
    val parser: RegexParser

    val output: OutputStrategy

    fun output(char: Char, buffer: StringBuilder)
}