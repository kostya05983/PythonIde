package controllers

import tornadofx.Controller
import scanner.Scanner
import scanner.Token
import syntax.SyntaxAnalyzer

class SyntaxAnalyzerImpl : Controller() {
    private val scanner = Scanner()
    private val analyzer = SyntaxAnalyzer()


    fun analyze(s: String): List<Token> {
        val list = scanner.scan(s)
        return analyzer.analyze(list)
    }
}