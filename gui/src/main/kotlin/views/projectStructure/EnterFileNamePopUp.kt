package views.projectStructure

import javafx.beans.property.SimpleStringProperty
import javafx.scene.Parent
import javafx.scene.paint.Color
import styles.projectStructure.EnterFileNamePopUpStyles
import tornadofx.*

class EnterFileNamePopUp : View() {
    override val root: Parent = Form()
    private val name = SimpleStringProperty()

    init {
        importStylesheet(EnterFileNamePopUpStyles::class)

        with(root) {
            style {
                backgroundColor += ColorHolder.secondColor
                textFill = ColorHolder.fontColor

            }
            fieldset {
                field("FileName") {
                    style {
                        textFill = ColorHolder.fontColor
                    }
                    textfield() {
                    }.bind(name)
                }
            }
            button("Ok") {
                style {
                    backgroundColor += ColorHolder.selectionColor
                }
                setOnMouseClicked {
                    if (it.clickCount == 1) {
                        fire(NewFileEvent(name.value))
                    }
                }
            }
        }
    }
}