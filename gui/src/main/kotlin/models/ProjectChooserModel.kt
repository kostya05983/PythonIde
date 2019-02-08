package models

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class ProjectChooserModel {
    val file = File("/home/kostya05983/.pythonIde/recentProjects.pyth")

    /**
     * creates file if not find this
     */
    private fun ifFileNotExistCreate() {
        if (!file.exists()) {
            file.createNewFile()
        }
    }

    /**
     * path to it .pythonIde/recentProjects.pyth
     * get recent Projects
     * *****format*****
     *  name, path
     *  ***************
     */
    fun getRecentProjects(): List<Project> {
        ifFileNotExistCreate()
        val bufferedReader = BufferedReader(FileReader(file))
        val result = ArrayList<Project>()
        while (bufferedReader.ready()) {
            val line = bufferedReader.readLine()
            val name = line.substringBefore(",")
            val path = File(line.substringAfter(","))
            result.add(Project(name, path))
        }
        bufferedReader.close()
        return result
    }
}