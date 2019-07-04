package org.ide.projectStructure.view

import javafx.geometry.Orientation
import javafx.scene.Parent
import javafx.scene.input.MouseButton
import javafx.stage.StageStyle
import org.ide.common.ColorHolder
import tornadofx.*

class CreatePopup : View() {
    override val root: Parent = listmenu {
        orientation = Orientation.VERTICAL
        minHeight = 50.0
        minWidth = 80.0

        //TODO change text color
        style {
            baseColor = ColorHolder.secondColor
            backgroundColor += ColorHolder.secondColor
            textFill = ColorHolder.fontColor
        }

        item("New") {
            setOnMouseClicked {
                if (it.button == MouseButton.PRIMARY) {
                    find<EnterFileNamePopUp>().openModal(stageStyle = StageStyle.UTILITY)
                }
            }

            style {
                baseColor = ColorHolder.secondColor
                backgroundColor += ColorHolder.secondColor
                textFill = ColorHolder.fontColor
            }
        }
    }
}