package models

import views.projectStructure.SubDirectory
import java.io.File

class TreeProjectModel(private val file: File) {

    fun getStructure(): List<SubDirectory> {
        val listSubdirectories = mutableListOf<SubDirectory>()
        loadDirectory(listSubdirectories, file)
        return listSubdirectories
    }

    private fun loadDirectory(listOut: MutableList<SubDirectory>, file: File) {
        if (file.isDirectory) {
            val parentName = file.name
            for (child in file.listFiles()) {
                listOut.add(SubDirectory(parentName, child.name))
                loadDirectory(listOut, child)
            }
        }
    }
}