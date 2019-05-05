package views

import ColorHolder
import javafx.scene.Parent
import javafx.scene.layout.Priority
import models.OutputConsoleToView
import styles.OutputerStyles
import tornadofx.*
import tornadofx.Stylesheet.Companion.content

//MOVE to MVVM
class OutPuter : View() {
    private val outputerConsoleToView: OutputConsoleToView by inject()

    init {
        importStylesheet(OutputerStyles::class)
    }

    override val root: Parent = textarea {
        isEditable = false

        subscribe<OutputEvent> {
            text += it.line;
        }

        subscribe<ClearOutputEvent> {
            text = ""
        }

        subscribe<OutputEventLn> {
            text = text + it.line+"\n"
        }
    }
}

class OutputEvent(val line: String) : FXEvent()

class OutputEventLn(val line: String) : FXEvent()

class ClearOutputEvent() : FXEvent()
