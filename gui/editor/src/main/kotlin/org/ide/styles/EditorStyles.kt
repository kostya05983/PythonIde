package org.ide.styles

import org.ide.common.ColorHolder
import tornadofx.Dimension
import tornadofx.Stylesheet
import tornadofx.box

class EditorStyles: Stylesheet() {

    init {
        Stylesheet.textArea {
            Stylesheet.content {
                backgroundColor += ColorHolder.primaryColor
                borderWidth += box(Dimension(0.0, Dimension.LinearUnits.px))
            }
            Stylesheet.focused {
                backgroundColor += ColorHolder.primaryColor
            }

            backgroundInsets += box(Dimension(0.0, Dimension.LinearUnits.px))
            baseColor = ColorHolder.primaryColor
            focusColor = ColorHolder.primaryColor
            textFill = ColorHolder.fontColor
        }
    }
}