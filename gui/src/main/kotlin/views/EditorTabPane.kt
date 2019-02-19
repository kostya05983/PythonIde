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
            //TODO необходимо добавить editor так чтобы он попал в общий поток событий

            currentEditor = find(mapOf(Editor::path to event.path))

            tab(currentEditor!!) {
                text = parseName(event.path)
            }
        }
        loadSubscriptions()
    }

    //TODO немного неправильно что события отправляемые по сути на edtior буду обрабатываться в оборачивюащем компоненент
    //TODO в идеале разделить
    private fun loadSubscriptions() {
        subscribe<WriteEvent> {
            println("Kek")
        }
    }

    //TODO divide into branches for unix and windows systems
    private fun parseName(path: String): String {
        //for winddows
        return path.substringAfterLast("\\")
        //for unix
        //return path.substringAfterLast(":")
    }
}

class CreateEditorEvent(val path: String) : FXEvent()