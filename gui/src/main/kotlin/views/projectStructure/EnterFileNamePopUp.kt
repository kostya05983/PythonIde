package views.projectStructure

import javafx.beans.property.SimpleStringProperty
import javafx.scene.Parent
import javafx.scene.paint.Color
import tornadofx.*

class EnterFileNamePopUp : View() {
    override val root: Parent = Form()
    private val name = SimpleStringProperty()

    init {
        with(root) {
            fieldset {
                field("FileName") {
                    textfield().bind(name)
                }
            }
            button("Ok") {
                style {
                    backgroundColor += Color.BLACK
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