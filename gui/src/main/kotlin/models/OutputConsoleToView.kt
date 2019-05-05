package models

import lab2automat.OutputStrategy
import tornadofx.Controller
import views.ClearOutputEvent
import views.OutputEvent
import views.OutputEventLn

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