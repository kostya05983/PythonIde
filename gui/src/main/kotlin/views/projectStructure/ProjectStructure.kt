package views.projectStructure

import javafx.scene.Parent
import javafx.scene.control.TreeItem
import models.TreeProjectModel
import tornadofx.*
import java.io.File

class ProjectStructure(file: File) : View() {
    private val model = TreeProjectModel(file)

    override val root: Parent = treeview<String>() {
        // Generate items. Children of the root item will contain departments
        val head = model.getStructure()
        root = head
        cellFormat {
            text = it
        }
//        populate { parent ->
//            directories.filter { it.parent == parent.value.parent }
//        }
    }

}


data class SubDirectory(val parent: String, var child: String?)