package models

import lab2automat.OutputStrategy
import tornadofx.Controller
import views.OutputEvent

class OutputConsoleToView : Controller(), OutputStrategy {
    override fun print(s: String) {
        fire(OutputEvent(s))
    }
}