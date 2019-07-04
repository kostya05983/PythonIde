package views

import javafx.scene.Parent
import javafx.scene.layout.Priority
import org.ide.common.ColorHolder
import org.ide.projectStructure.view.ProjectStructure
import org.ide.view.EditorTabPane
import org.ide.view.OutPuter
import tornadofx.*
import views.menu.MenuView
import views.projectChoser.ProjectChooser
import java.io.File


//TODO divide styles in diffetenrt locations, folder styles and ceate a c;ass style for each view
//TODO it helps more docomposition and edit view more easily, because we have view as structure of out view
//TODO and style as appearance of our view
//TODO add loggings to vew
class MainView(private val directory: File) : View() {
    override val root: Parent = hbox {
        style {
            backgroundColor += ColorHolder.secondColor
        }

        val projectStructure = find<ProjectStructure>(mapOf(ProjectStructure::file to directory))
        add(projectStructure)

        vbox {
            style {
                hgrow = Priority.ALWAYS
                backgroundColor += ColorHolder.secondColor
            }
            add<MenuView>()
            add<EditorTabPane>()
            region {
                style {
                    minHeight = Dimension(20.0, Dimension.LinearUnits.px)
                }
            }
            add<OutPuter>()
        }

        subscribe<MyEvent> {
            replaceWith(ProjectChooser::class)
        }
    }


}

class MyEvent: FXEvent()