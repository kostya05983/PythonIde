package views.projectStructure

import javafx.scene.Parent
import tornadofx.View
import tornadofx.populate
import tornadofx.treeview

class ProjectStructure : View() {
    override val root: Parent = treeview<String>() {

    }

}


data class SubDirectory(val parent: String, var child: String?)