package models

import javafx.scene.control.TreeItem
import java.io.File

class TreeProjectModel(private val file: File) {

    fun getStructure(): TreeItem<String> {
        val head = TreeItem<String>(file.name)
        loadDirectory(head, file)
        return head
    }

    /**
     * fun loads project structure in tree
     * @param parent - previous element
     * @param file - root file of project
     */
    private fun loadDirectory(parent: TreeItem<String>, file: File) {
        if (file.isDirectory) {
            for (child in file.listFiles()) {
                val element = TreeItem<String>(child.name)
                parent.children.add(element)
                loadDirectory(element, child)
            }
        }
    }
}