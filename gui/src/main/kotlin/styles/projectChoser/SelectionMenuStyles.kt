package styles.projectChoser

import javafx.scene.paint.Color
import org.ide.common.ColorHolder
import tornadofx.Stylesheet

class SelectionMenuStyles: Stylesheet() {

    init {
        Stylesheet.button {
            backgroundColor += Color.TRANSPARENT
            textFill = ColorHolder.fontColor
        }
    }
}