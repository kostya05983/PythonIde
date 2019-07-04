package org.ide.styles

import org.ide.common.ColorHolder
import tornadofx.Dimension
import tornadofx.Stylesheet
import tornadofx.box
import tornadofx.stylesheet

class OutputerStyles: Stylesheet() {

    init {

        Stylesheet.textArea {
            content {
                backgroundColor += ColorHolder.primaryColor
            }
            Stylesheet.focused {
                backgroundColor += ColorHolder.primaryColor
            }

            backgroundInsets += box(Dimension(0.0, Dimension.LinearUnits.px))
            baseColor = ColorHolder.primaryColor
            focusColor = ColorHolder.primaryColor
        }
    }
}