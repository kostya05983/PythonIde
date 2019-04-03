package lab3regex.automat

import lab2automat.OutputStrategy
import lab3regex.RegexParser

interface State {


    val parser: RegexParser

    val output: OutputStrategy

    fun output(char: Char)
}