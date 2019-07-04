package org.ide.controllers

import lab3regex.RegexParser
import org.ide.models.OutputConsoleToView
import org.ide.view.ClearOutputEvent
import tornadofx.Controller

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