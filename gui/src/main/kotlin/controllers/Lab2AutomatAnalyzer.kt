package controllers

import lab2automat.Parser
import models.OutputConsoleToView
import tornadofx.Controller
import views.ClearOutputEvent
import views.OutputEventLn

class Lab2AutomatAnalyzer : SyntaxAnylyzer, Controller() {
    override fun analyze(s: String) {
        val parser = Parser(OutputConsoleToView())
        fire(ClearOutputEvent())
        s.split("\n").forEach {
            parser.parse(it)
        }
    }
}