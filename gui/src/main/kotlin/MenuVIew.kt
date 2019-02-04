import javafx.scene.Parent
import tornadofx.*
import tornadofx.Stylesheet.Companion.textArea

class MenuView : View() {
    override val root: Parent = vbox {
        menubar {
            menu("File") {
                item("New")
                item("Open Recent")
                item("Close project")
                item("Project Structure")
                item("Exit")
            }
            menu("Edit") {
                item("Undo")
                item("Redo")
                item("Copy")
                item("Paste")
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
        textarea {
         prefRowCount = 5
        }
    }


}