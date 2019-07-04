package org.ide.projectStructure.styles

import org.ide.common.ColorHolder
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