package org.ide.projectStructure.styles

import org.ide.common.ColorHolder
import tornadofx.Stylesheet

class ProjectStructureStyles : Stylesheet() {
    init {
        Stylesheet.treeView {
            backgroundColor += ColorHolder.secondColor
            Stylesheet.treeCell {
                backgroundColor += ColorHolder.secondColor
                textFill = ColorHolder.fontColor
            }
        }
    }
}