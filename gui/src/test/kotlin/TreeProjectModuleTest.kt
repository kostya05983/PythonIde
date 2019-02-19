import models.ProjectStructureModel
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import views.projectStructure.SubDirectory
import java.io.File

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TreeProjectModuleTest {
    lateinit var generator: Generator
    lateinit var fileTest: File

    @BeforeAll
    fun setUp() {
        generator = Generator()
    }

    @AfterEach
    fun tearDown() {
        fileTest.deleteRecursively()
    }

    @RepeatedTest(1000)
    fun getSubdirectories() {
        fileTest = File("testFile")
        fileTest.mkdir()
        val subDirectoryName = createSubdirectory(fileTest)
        val treeProjectModel = ProjectStructureModel(fileTest)
        val structures = treeProjectModel.getStructure()
        assertEquals(SubDirectory(fileTest.name, subDirectoryName), structures)
    }

    private fun createSubdirectory(file: File): String {
        val generateStr = generator.generateStr()
        if (file.isDirectory) {
            val newFile = File(file, generateStr)
            newFile.createNewFile()
        }
        return generateStr
    }
}