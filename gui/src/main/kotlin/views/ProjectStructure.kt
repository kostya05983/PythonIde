package views

import javafx.scene.Parent
import tornadofx.View
import tornadofx.vbox
import java.io.File

class ProjectStructure(private val directory: File) : View() {
    override val root: Parent = vbox {
        println(directory.walkTopDown())
    }


}