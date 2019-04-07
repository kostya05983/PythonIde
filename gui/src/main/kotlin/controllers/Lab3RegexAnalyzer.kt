package controllers

import lab3regex.RegexParser
import models.OutputConsoleToView
import tornadofx.Controller
import views.ClearOutputEvent

/**
 * @author kostya05983
 * this analyzer search regex in typed text
 */
class Lab3RegexAnalyzer : SyntaxAnylyzer, Controller() {
    override fun analyze(s: String) {
        val parser = RegexParser(OutputConsoleToView())
        fire(ClearOutputEvent())
        parser.parse(s)
    }
}