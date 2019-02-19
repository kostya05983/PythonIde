package views

import ColorHolder
import javafx.scene.Parent
import javafx.scene.layout.Priority
import tornadofx.*
import views.projectStructure.ProjectStructure
import java.io.File


//TODO divide styles in diffetenrt locations, folder styles and ceate a c;ass style for each view
//TODO it helps more docomposition and edit view more easily, because we have view as structure of out view
//TODO and style as appearance of our view
//TODO add loggings to vew
class MenuView(private val directory: File) : View() {
    private val outPuter: OutPuter by inject()
    private val editorTabPane: EditorTabPane by inject()

    override val root: Parent = hbox {
        style {
            backgroundColor += ColorHolder.secondColor
        }
        val projectStructure = ProjectStructure(directory)
        add(projectStructure)
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
                    item("Save") {
                        action {
                            fire(WriteEvent())
                        }
                    }
                    item("Save all")
                    item("Exit")
                }
                menu("Editor") {
                    item("Undo") {
                        action {
                            editorTabPane.currentEditor?.root?.undo()
                        }
                    }
                    item("Redo") {
                        action {
                            editorTabPane.currentEditor?.root?.redo()
                        }
                    }
                    item("Cut") {
                        action {
                            editorTabPane.currentEditor?.root?.undo()
                        }
                    }
                    item("Copy") {
                        action {
                            editorTabPane.currentEditor?.root?.copy()
                        }
                    }
                    item("Paste") {
                        action {
                            editorTabPane.currentEditor?.root?.paste()
                        }
                    }
                    item("Delete") {
                        action {
                            editorTabPane.currentEditor?.root?.deleteSelectedText()
                        }
                    }
                    item("Select all") {
                        action {
                            editorTabPane.currentEditor?.root?.selectAll()
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

            add(editorTabPane)

            region {
                style {
                    minHeight = Dimension(20.0, Dimension.LinearUnits.px)
                }
            }
            add(outPuter)
        }
    }
}