package controllers

import tornadofx.Controller
import scanner.Scanner
import syntax.SyntaxAnalyzer

class SyntaxAnalyzerImpl : Controller(), SyntaxAnylyzer {
    private val scanner = Scanner()
    private val analyzer = SyntaxAnalyzer()


    override fun analyze(s: String) {
        val list = scanner.scan(s)
        analyzer.analyze(list)
    }
}