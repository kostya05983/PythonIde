package views

import ColorHolder
import javafx.scene.Parent
import javafx.scene.layout.Priority
import tornadofx.*
import tornadofx.Stylesheet.Companion.content

class OutPuter : View() {
    override val root: Parent = textarea {
        stylesheet {
            content {
                backgroundColor += ColorHolder.primaryColor
            }
            Stylesheet.focused {
                backgroundColor += ColorHolder.primaryColor
            }
        }
        style {
//            startMargin = Dimension(20.0, Dimension.LinearUnits.px)
            backgroundInsets += box(Dimension(0.0, Dimension.LinearUnits.px))
            baseColor = ColorHolder.primaryColor
            focusColor = ColorHolder.primaryColor
//            vgrow = Priority.ALWAYS
        }
        isEditable = false
    }


}