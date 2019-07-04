package org.ide.models

import lab2automat.OutputStrategy
import org.ide.view.ClearOutputEvent
import org.ide.view.OutputEvent
import org.ide.view.OutputEventLn
import tornadofx.Controller

class OutputConsoleToView : Controller(), OutputStrategy {
    override fun println(s: String) {
        fire(OutputEventLn(s))
    }

    override fun clear() {
        fire(ClearOutputEvent())
    }

    override fun print(s: String) {
        fire(OutputEvent(s))
    }
}