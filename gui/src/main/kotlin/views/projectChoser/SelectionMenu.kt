package views.projectChoser

import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.layout.Priority
import styles.projectChoser.SelectionMenuStyles
import tornadofx.*
import views.MainView

class SelectionMenu : View() {
    init {
        importStylesheet(SelectionMenuStyles::class)
    }

    override val root: Parent = vbox {
        vgrow = Priority.ALWAYS
        alignment = Pos.CENTER
        hgrow = Priority.ALWAYS
        //RIGHT PART
        button {
            text = "Open project"
            action {
                val file = chooseDirectory { }
                if (file != null)
                    MainView(file).openWindow()
            }
        }
        button {
            text = "Import from vcs"
        }
    }
}