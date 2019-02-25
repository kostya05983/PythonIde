package styles.projectStructure

import tornadofx.Stylesheet

class EnterFileNamePopUpStyles : Stylesheet() {

    init {
        Stylesheet.form {
            backgroundColor += ColorHolder.secondColor
            textFill = ColorHolder.fontColor
        }

        Stylesheet.button {
            backgroundColor += ColorHolder.selectionColor
        }

    }
}