package views.projectStructure

import javafx.event.EventHandler
import javafx.scene.Parent
import javafx.scene.input.MouseButton
import javafx.scene.paint.Color
import models.ItemData
import models.TreeProjectModel
import tornadofx.*
import views.CreateEditorEvent
import views.EditorTabPane
import java.io.File

class ProjectStructure(file: File) : View() {
    private val model = TreeProjectModel(file)

    init {
        find(EditorTabPane::class)
    }

    override val root: Parent = treeview<ItemData>() {
        // Generate items. Children of the root item will contain departments
        val head = model.getStructure()
        root = head
        cellFormat {
            text = it.name
            style {
                backgroundColor += ColorHolder.secondColor
                textFill = ColorHolder.fontColor
            }
        }

        style {
            backgroundColor += Color.TRANSPARENT
            baseColor = ColorHolder.secondColor
            fill = ColorHolder.secondColor
        }

        stylesheet {
            Stylesheet.content {
                //                backgroundColor += ColorHolder.secondColor
                backgroundColor += Color.TRANSPARENT
                baseColor = ColorHolder.secondColor

            }
        }

        setOnMouseClicked {
            if (it.button == MouseButton.PRIMARY) {
                if (it.clickCount == 2) {
                    fire(CreateEditorEvent(this.selectionModel.selectedItem.value.absolutelyPath))
                    System.out.println("Double clicked");
                }
            }
        }
    }

}


data class SubDirectory(val parent: String, var child: String?)