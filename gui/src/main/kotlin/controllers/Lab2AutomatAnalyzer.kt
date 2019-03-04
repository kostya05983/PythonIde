package controllers

import lab2automat.Parser
import models.OutputConsoleToView
import tornadofx.Controller

class Lab2AutomatAnalyzer : SyntaxAnylyzer, Controller() {
    override fun analyze(s: String) {
        val parser = Parser(OutputConsoleToView())
        s.split("\n").forEach {
            parser.parse(it)
        }
    }
}