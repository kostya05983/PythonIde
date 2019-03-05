package styles

import tornadofx.Stylesheet

class EditorTabStyles : Stylesheet() {


    init {
        Stylesheet.tab {
            backgroundColor += ColorHolder.secondColor
        }
        Stylesheet.tabLabel {
            textFill = ColorHolder.fontColor
        }
        Stylesheet.text {
            textFill = ColorHolder.fontColor
        }
    }

}