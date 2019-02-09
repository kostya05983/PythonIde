package views

import ColorHolder
import javafx.scene.Parent
import javafx.scene.layout.Priority
import tornadofx.*
import java.io.File

class MenuView(private val directory: File) : View() {
    private val editor = Editor()
    private val outPuter = OutPuter()

    override val root: Parent = hbox {
        style {
            backgroundColor += ColorHolder.primaryColor
        }
        vbox {
            style {
                hgrow = Priority.ALWAYS
                backgroundColor += ColorHolder.secondColor
            }
            menubar {
                stylesheet {
                    Stylesheet.content {
                        backgroundColor += ColorHolder.secondColor
                    }

                    Stylesheet.menu {
                        backgroundColor += ColorHolder.secondColor
                        baseColor = ColorHolder.secondColor
                    }
                    Stylesheet.menuItem {
                        backgroundColor += ColorHolder.secondColor
                        baseColor = ColorHolder.secondColor
                    }
                }
                style {
                    baseColor = ColorHolder.secondColor
                    backgroundColor += ColorHolder.secondColor
                    textFill = ColorHolder.fontColor
                }
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
                        action { editor.root.undo() }
                    }
                    item("Redo") {
                        action { editor.root.redo() }
                    }
                    item("Cut") {
                        action { editor.root.cut() }
                    }
                    item("Copy") {
                        action { editor.root.copy() }
                    }
                    item("Paste") {
                        action { editor.root.paste() }
                    }
                    item("Delete") {
                        action { editor.root.deleteSelectedText() }
                    }
                    item("Select all") {
                        action { editor.root.selectAll() }
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
            region {
                style {
                    minHeight = Dimension(20.0, Dimension.LinearUnits.px)
                }
            }
            add(outPuter)
        }
    }


}