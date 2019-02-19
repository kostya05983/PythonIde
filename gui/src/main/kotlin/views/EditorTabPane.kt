package views

import javafx.scene.control.TabPane
import javafx.scene.layout.Priority
import systemdeterminer.InfoSystem
import systemdeterminer.Systems
import tornadofx.*

class EditorTabPane : View() {
    var currentEditor: Editor? = null

    /**
     * init our tabpane with editor and fileName
     */
    override val root: TabPane = tabpane {
        style {
            vgrow = Priority.ALWAYS
        }

        subscribe<CreateEditorEvent> { event ->
            currentEditor = find(mapOf(Editor::path to event.path))

            selectionModel.select(
                    tab(parseName(event.path)) {
                        add(currentEditor!!)
                    }
            )
        }
        loadSubscriptions()
    }

    /**
     * fun loads subscriptions for weriting in file
     */
    private fun loadSubscriptions() {
        subscribe<WriteEvent> {
            println("Kek")
        }
    }

    /**
     * get fileName from path
     * @param path - path opening file
     */
    private fun parseName(path: String): String {
        val infoSystem = InfoSystem()
        return when (infoSystem.getType()) {
            Systems.Windows -> path.substringAfterLast("\\")
            Systems.MacOs -> TODO()
            Systems.Linux -> path.substringAfterLast("/")
            Systems.Other -> TODO()
        }
    }
}

class CreateEditorEvent(val path: String) : FXEvent()