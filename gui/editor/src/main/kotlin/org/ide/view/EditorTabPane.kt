package org.ide.view

import javafx.scene.control.TabPane
import javafx.scene.layout.Priority
import org.ide.styles.EditorTabStyles
import systemdeterminer.InfoSystem
import systemdeterminer.Systems
import tornadofx.*

class EditorTabPane : View() {
    var currentEditor: Editor? = null

    init {
        importStylesheet(EditorTabStyles::class)
    }

    /**
     * init our tabpane with editor and fileName
     */
    override val root: TabPane = tabpane {
        vgrow = Priority.ALWAYS

        subscribe<CreateEditorEvent> { event ->
            currentEditor = find(mapOf(Editor::path to event.path))

            selectionModel.select(
                    tab(parseName(event.path)) {
                        add(currentEditor!!)
                    }
            )
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