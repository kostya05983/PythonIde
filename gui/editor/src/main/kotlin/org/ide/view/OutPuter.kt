package org.ide.view

import javafx.scene.Parent
import org.ide.models.OutputConsoleToView
import org.ide.styles.OutputerStyles
import tornadofx.*

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
