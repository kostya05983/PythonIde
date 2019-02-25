package styles

import tornadofx.Stylesheet

class EditorTabStyles : Stylesheet() {


    init {
        Stylesheet.tab {
            textFill = ColorHolder.fontColor
            backgroundColor += ColorHolder.secondColor
            baseColor = ColorHolder.fontColor

        }
        Stylesheet.text {
            textFill  = ColorHolder.fontColor
        }
    }

}