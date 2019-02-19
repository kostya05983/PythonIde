package styles.menu

import tornadofx.Stylesheet

class MenuViewStyles : Stylesheet() {

    init {
        Stylesheet.content {
            backgroundColor += ColorHolder.secondColor
        }

        Stylesheet.menu {
            backgroundColor += ColorHolder.secondColor
            baseColor = ColorHolder.secondColor
            hover {
                backgroundColor += ColorHolder.selectionColor
            }
        }
        Stylesheet.menuItem {
            backgroundColor += ColorHolder.secondColor
            baseColor = ColorHolder.secondColor
        }
        Stylesheet.menuBar {
            baseColor = ColorHolder.secondColor
            backgroundColor += ColorHolder.secondColor
            textFill = ColorHolder.fontColor
        }
    }
}