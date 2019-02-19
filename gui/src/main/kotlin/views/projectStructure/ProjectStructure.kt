package views.projectStructure

import javafx.scene.Parent
import javafx.scene.input.MouseButton
import models.ItemData
import models.TreeProjectModel
import styles.projectStructure.ProjectStructureStyles
import tornadofx.View
import tornadofx.cellFormat
import tornadofx.importStylesheet
import tornadofx.treeview
import views.CreateEditorEvent
import views.EditorTabPane
import java.io.File

class ProjectStructure(file: File) : View() {
    private val model = TreeProjectModel(file)

    init {
        importStylesheet(ProjectStructureStyles::class)
        find(EditorTabPane::class)
    }

    override val root: Parent = treeview<ItemData> {
        // Generate items. Children of the root item will contain departments
        val head = model.getStructure()
        root = head
        cellFormat {
            text = it.name
        }

        setOnMouseClicked {
            if (it.button == MouseButton.PRIMARY) {
                if (it.clickCount == 2) {
                    fire(CreateEditorEvent(this.selectionModel.selectedItem.value.absolutelyPath))
                    System.out.println("Double clicked")
                }
            }
        }
    }

}


data class SubDirectory(val parent: String, var child: String?)