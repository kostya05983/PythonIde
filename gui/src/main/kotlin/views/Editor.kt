package views

import ColorHolder
import javafx.scene.control.TextArea
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import tornadofx.*

class Editor : View() {
    override val root: TextArea = textarea {
        stylesheet {
            Stylesheet.content {
                backgroundColor += ColorHolder.primaryColor
                borderWidth += box(Dimension(0.0, Dimension.LinearUnits.px))
            }
            Stylesheet.focused {
                backgroundColor += ColorHolder.primaryColor
            }
        }

        style {
            backgroundInsets += box(Dimension(0.0, Dimension.LinearUnits.px))
            baseColor = ColorHolder.primaryColor
            focusColor = ColorHolder.primaryColor
            textFill = ColorHolder.fontColor
            hgrow = Priority.ALWAYS
            vgrow = Priority.ALWAYS

        }
    }
}

fun TextArea.deleteSelectedText() {
    text.replace(selectedText, "")
}