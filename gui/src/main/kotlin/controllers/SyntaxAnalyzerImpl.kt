package controllers

import tornadofx.Controller
import scanner.ScannerAutomate
import scanner.Token
import syntax.SyntaxAnalyzer

class SyntaxAnalyzerImpl : Controller() {
    private val scanner = ScannerAutomate()
    private val analyzer = SyntaxAnalyzer()

    fun analyze(s: String): List<Token> {
        val list = scanner.scan(s)
        scanner.reset()
        return analyzer.analyze(list)
    }
}