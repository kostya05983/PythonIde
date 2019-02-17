package views

import javafx.scene.control.TabPane
import javafx.scene.layout.Priority
import tornadofx.*

class EditorTabPane : View() {
    var currentEditor: Editor? = null

    override val root: TabPane = tabpane {
        style {
            vgrow = Priority.ALWAYS

        }
        subscribe<CreateEditorEvent> { event ->
            currentEditor = Editor(event.path)
            

            tab(currentEditor!!) {
                text = parseName(event.path)
            }
        }
    }

    //TODO divide into branches for unix and windows systems
    private fun parseName(path: String): String {
        return path.substringAfterLast(":")
    }
}

class CreateEditorEvent(val path: String) : FXEvent()