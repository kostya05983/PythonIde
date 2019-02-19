package views.projectChoser

import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import styles.projectChoser.SelectionMenuStyles
import tornadofx.*
import views.MenuView

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
                    MenuView(file).openWindow()
            }
        }
        button {
            text = "Import from vcs"
        }
    }
}