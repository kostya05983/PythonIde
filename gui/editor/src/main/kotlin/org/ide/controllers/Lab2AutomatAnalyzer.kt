package org.ide.controllers

import lab2automat.Parser
import org.ide.models.OutputConsoleToView
import org.ide.view.ClearOutputEvent
import tornadofx.Controller

class Lab2AutomatAnalyzer : SyntaxAnylyzer, Controller() {
    override fun analyze(s: String) {
        val parser = Parser(OutputConsoleToView())
        fire(ClearOutputEvent())
        s.split("\n").forEach {
            parser.parse(it)
        }
    }
}