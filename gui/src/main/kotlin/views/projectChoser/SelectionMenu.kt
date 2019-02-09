package views.projectChoser

import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import tornadofx.*
import views.MenuView

class SelectionMenu : View() {
    override val root: Parent = vbox {
        style {
            vgrow = Priority.ALWAYS
            alignment = Pos.CENTER
            hgrow = Priority.ALWAYS
        }
        //RIGHT PART
        button {
            text = "Open project"
            style {
                backgroundColor += Color.TRANSPARENT
                textFill = ColorHolder.fontColor
            }
            action {
                val file = chooseDirectory { }
                if (file != null)
                    MenuView(file).openWindow()
            }
        }
        button {
            text = "Import from vcs"
            style {
                backgroundColor += Color.TRANSPARENT
                textFill = ColorHolder.fontColor
            }
        }
    }
}