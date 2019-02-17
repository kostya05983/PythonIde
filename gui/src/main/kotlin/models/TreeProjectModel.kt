package models

import javafx.scene.control.TreeItem
import java.io.File

class TreeProjectModel(private val file: File) {

    fun getStructure(): TreeItem<ItemData> {
        val head = TreeItem<ItemData>(ItemData(file.name, file.absolutePath))
        loadDirectory(head, file)
        return head
    }

    /**
     * fun loads project structure in tree
     * @param parent - previous element
     * @param file - root file of project
     */
    private fun loadDirectory(parent: TreeItem<ItemData>, file: File) {
        if (file.isDirectory) {
            for (child in file.listFiles()) {
                val element = TreeItem<ItemData>(ItemData(child.name, child.absolutePath))
                parent.children.add(element)
                loadDirectory(element, child)
            }
        }
    }
}

data class ItemData(val name: String, val absolutelyPath: String)