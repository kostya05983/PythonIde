package models

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class ListOfProjectModel {
    companion object {
        private const val WINDOWS_NAME = "Windows 10"
        private const val LINUX_NAME = ""
    }

    //TODO avoid absolutely pathes
    private val file = if (System.getProperty("os.name").equals(WINDOWS_NAME))
        File("C:\\Users\\kosty\\pythonIde\\recentProjects.pyth")
    else File("/home/kostya05983/.pythonIde/recentProjects.pyth")

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