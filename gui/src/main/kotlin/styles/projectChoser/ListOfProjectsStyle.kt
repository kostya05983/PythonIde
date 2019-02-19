package styles.projectChoser

import ColorHolder
import javafx.geometry.Pos
import javafx.scene.control.ScrollPane
import javafx.scene.paint.Color
import tornadofx.Stylesheet

class ListOfProjectsStyle : Stylesheet() {


    init {
        Stylesheet.listView {
            Stylesheet.listCell {
                textFill = Color.WHITE
                alignment = Pos.CENTER_LEFT
                hover {
                    backgroundColor += ColorHolder.selectionColor
                }
            }

            backgroundColor += Color.TRANSPARENT

            fitToHeight = true

            vBarPolicy = ScrollPane.ScrollBarPolicy.ALWAYS

            rowSelection {
                backgroundColor += ColorHolder.selectionColor
                baseColor = ColorHolder.selectionColor
                focusColor = ColorHolder.selectionColor
            }

        }
        //Настройки самой панели
        Stylesheet.scrollPane {
            backgroundColor += ColorHolder.primaryColor
            baseColor = Color.TRANSPARENT
            vBarPolicy = ScrollPane.ScrollBarPolicy.ALWAYS
        }

        //Настройки скролла
        vertical {
            backgroundColor += ColorHolder.primaryColor
            thumb {
                backgroundColor += ColorHolder.secondColor
            }

            incrementButton {
                incrementArrow {
                    baseColor = ColorHolder.fontColor
                }
            }
            decrementButton {
                decrementArrow {
                    baseColor = ColorHolder.fontColor
                }
            }
            fitToHeight = true
            vBarPolicy = ScrollPane.ScrollBarPolicy.ALWAYS
        }

    }
}