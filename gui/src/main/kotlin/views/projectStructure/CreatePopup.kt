package views.projectStructure

import javafx.geometry.Orientation
import javafx.scene.Parent
import javafx.scene.input.MouseButton
import javafx.stage.StageStyle
import tornadofx.*

class CreatePopup : View() {
    override val root: Parent = listmenu {
        orientation = Orientation.VERTICAL
        minHeight = 50.0
        minWidth = 80.0

        item {
            text = "New"
            setOnMouseClicked {
                if (it.button == MouseButton.PRIMARY) {
                    find<EnterFileNamePopUp>().openModal(stageStyle = StageStyle.UTILITY)
                }
            }
        }
    }
}