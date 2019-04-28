package views.menu

import javafx.scene.Parent
import styles.menu.MenuViewStyles
import tornadofx.*
import views.*
import views.projectChoser.ProjectChooser
import views.projectStructure.OpenEvent

class MenuView : View() {
    private val editorTabPane: EditorTabPane by inject()

    init {
        importStylesheet(MenuViewStyles::class)
    }

    override val root: Parent = menubar {
        menu("File") {
            item("New")
            item("Open") {
                action {
                    val file = chooseDirectory { }
                    if (file != null)
                        fire(OpenEvent(file))
                }
            }
            item("Open Recent")
            item("Close project") {
                action {
                    fire(MyEvent())
                }
            }
            item("Project Structure")
            item("Save") {
                action {
                    fire(WriteEvent())
                }
            }
            item("Save all")
            item("Exit") {
                action {
                    close()
                }
            }
        }
        menu("Editor") {
            item("Undo", "Shortcut+Z") {

                action {
                    editorTabPane.currentEditor?.codeArea?.undo()
                }
            }
            item("Redo", "Shortcut+Shift+Z") {
                action {
                    editorTabPane.currentEditor?.codeArea?.redo()
                }
            }
            item("Cut", "Shortcut+X") {
                action {
                    editorTabPane.currentEditor?.codeArea?.undo()
                }
            }
            item("Copy", "Shortcut+C") {
                action {
                    editorTabPane.currentEditor?.codeArea?.copy()
                }
            }
            item("Paste", "Shortcut+V") {
                action {
                    editorTabPane.currentEditor?.codeArea?.paste()
                }
            }
            item("Delete", "Shortcut+Y") {
                action {
                    TODO("delete selected text")
//                    editorTabPane.currentEditor?.codeArea?.deleteSelectedText()
                }
            }
            item("Select all", "Shortcut+A") {
                action {
                    editorTabPane.currentEditor?.codeArea?.selectAll()
                }
            }
        }
        menu("View") {
            item("Tool")
            item("Project Bar")
            //e.t.c
        }
        menu("Code") {
            item("Reformat Code")
            //e.t.c
        }
        menu("Analyze") {
            item("Code cleanup")
            item("Inspections")
        }
        menu("Refactor") {
            item("Rename")
            //e.t.c
        }
        menu("Build") {
            item("Build Project")
            item("Rebuild Project")
            item("Build Module Apk")
        }
        menu("Run") {
            item("Run")
            item("Debug")
            //e.t.c
        }
        menu("Tools") {
            //e.t.c
        }
        menu("Vcs") {
            //git,  .e.t.c
        }
        menu("Window") {
            item("Restore Default Layout")
        }
        menu("Help") {
            item("Find Action")
            item("Help")
            item("About")
        }
    }

}