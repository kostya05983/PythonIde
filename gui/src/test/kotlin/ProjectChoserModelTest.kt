import models.ProjectChooserModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File
import java.io.FileOutputStream

class ProjectChoserModelTest {

    @Test
    fun getRecentProject() {
        val file = File("/home/kostya05983/.pythonIde/recentProjects.pyth")
        file.createNewFile()
        val fileOutputStream = FileOutputStream(file)
        fileOutputStream.write("""
            test,/home/kostya05983/
            test1,/home/kostya05983/Projects/
        """.trimIndent().toByteArray())
        fileOutputStream.close()
        val result = ProjectChooserModel().getRecentProjects()
        assertEquals("test", result[0].name)
        assertEquals(File("/home/kostya05983"), result[0].path)
        assertEquals("test1", result[1].name)
        assertEquals(File("/home/kostya05983/Projects"), result[1].path)
    }
}