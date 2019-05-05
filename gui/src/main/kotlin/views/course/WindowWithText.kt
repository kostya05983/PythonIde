package views.course

import javafx.scene.Parent
import tornadofx.*

class WindowWithText(val text: String) : Fragment() {

    override val root: Parent = vbox {
        textarea {
            style {
                textFill = ColorHolder.fontColor
            }
            stylesheet {
                Stylesheet.content {
                    backgroundColor += ColorHolder.primaryColor
                    textFill = ColorHolder.fontColor
                }
                Stylesheet.text {
                    baseColor = ColorHolder.fontColor
                }
            }

            text = this@WindowWithText.text
        }
    }
}