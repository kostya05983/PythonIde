package views.projectStructure

import javafx.scene.Parent
import javafx.scene.input.MouseButton
import javafx.stage.StageStyle
import models.ItemData
import models.ProjectStructureModel
import styles.projectStructure.ProjectStructureStyles
import tornadofx.*
import views.CreateEditorEvent
import views.EditorTabPane
import java.io.File

class ProjectStructure() : Fragment() {
    val file: File by param()

    private val model = ProjectStructureModel(file)
    var selected: String? = null

    init {
        importStylesheet(ProjectStructureStyles::class)
        find(EditorTabPane::class)
        loadSubscriptions()
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
                if (it.clickCount == 2 && !isDirectory(this.selectionModel.selectedItem.value.absolutelyPath)) {
                    fire(CreateEditorEvent(this.selectionModel.selectedItem.value.absolutelyPath))
                    System.out.println("Double clicked")
                }
            }
            if (it.button == MouseButton.SECONDARY) {
                find<CreatePopup>().openModal(stageStyle = StageStyle.UTILITY)
                selected = this.selectionModel.selectedItem.value.absolutelyPath
            }
        }
    }

    private fun isDirectory(path: String): Boolean {
        val file = File(path)
        return file.isDirectory
    }

    private fun loadSubscriptions() {
        subscribe<NewFileEvent> {
            model.createNewFile(selected!!, it.name)
            //reload
            val projectStructure = find<ProjectStructure>(mapOf(ProjectStructure::file to file))
            replaceWith(projectStructure)
        }

        subscribe<OpenEvent> {
            val projectStructure = find<ProjectStructure>(mapOf(ProjectStructure::file to it.file))
            replaceWith(projectStructure)
        }
    }

}

class NewFileEvent(val name: String) : FXEvent()

//TODO это очень примитивный релод
class ReloadEvent() : FXEvent()

class OpenEvent(val file: File) : FXEvent()


data class SubDirectory(val parent: String, var child: String?)