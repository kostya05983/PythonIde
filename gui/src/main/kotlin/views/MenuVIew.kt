package views

import javafx.scene.Parent
import tornadofx.*
import java.io.File

class MenuView(private val directory: File) : View() {
    private val editor = Editor()
    private val outPuter = Outputer()
//    private val projectStructure = ProjectStructure(directory)

    override val root: Parent = hbox {
        //        add(projectStructure)
        vbox {
            menubar {
                menu("File") {
                    item("New")
                    item("Open Recent")
                    item("Close project")
                    item("Project Structure")
                    item("Save")
                    item("Save all")
                    item("Exit")
                }
                menu("Editor") {
                    item("Undo") {
                        action { editor.undo() }
                    }
                    item("Redo") {
                        action { editor.redo() }
                    }
                    item("Cut") {
                        action { editor.cut() }
                    }
                    item("Copy") {
                        action { editor.copy() }
                    }
                    item("Paste") {
                        action { editor.paste() }
                    }
                    item("Delete") {
                        action { editor.deleteSelectedText() }
                    }
                    item("Select all") {
                        action { editor.selectAll() }
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
            add(editor)
            add(outPuter)
        }
    }


}