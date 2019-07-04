package org.ide.projectStructure.view


import javafx.beans.property.SimpleStringProperty
import javafx.scene.Parent
import org.ide.common.ColorHolder
import org.ide.projectStructure.styles.EnterFileNamePopUpStyles
import tornadofx.*

class EnterFileNamePopUp : View() {
    override val root: Parent = Form()
    private val name = SimpleStringProperty()

    init {
        importStylesheet(EnterFileNamePopUpStyles::class)

        with(root) {
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
                setOnMouseClicked {
                    if (it.clickCount == 1) {
                        fire(NewFileEvent(name.value))
                    }
                }
            }
        }
    }
}