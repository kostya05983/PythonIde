package views.projectChoser

import ColorHolder
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.layout.Priority
import tornadofx.*

/**
 * element color #3e3e44
 * editor color #2b2b2b
 *
 */

class ProjectChooser : View() {

    override val root: Parent =
            hbox {
                vgrow = Priority.ALWAYS
                style {
                    backgroundColor += ColorHolder.secondColor
                    minWidth = Dimension(600.0, Dimension.LinearUnits.px)
                    minHeight = Dimension(400.0, Dimension.LinearUnits.px)
                }
                //LEFT PART
                add(ListOfProjects())
                //RIGHT PART
                add(SelectionMenu())
            }

}