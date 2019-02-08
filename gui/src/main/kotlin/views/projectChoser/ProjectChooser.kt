package views.projectChoser

import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.control.ListView
import javafx.scene.control.SelectionMode
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import models.Project
import models.ProjectChooserModel
import tornadofx.*
import views.MenuView

/**
 * element color #3e3e44
 * editor color #2b2b2b
 *
 */

class ProjectChooser : View() {
    private val model = ProjectChooserModel()

    override val root: Parent = vbox {
        style {
            backgroundColor += c("#3e3e44")
            alignment = Pos.CENTER
        }
        button {
            text = "Open project"
            style {
                backgroundColor += Color.TRANSPARENT
                textFill = Color.WHITE
            }
            action {
                val file = chooseDirectory { }
                if (file != null)
                    MenuView(file).openWindow()
            }
        }
        button {
            text = "Import from vcs"
            style {
                backgroundColor += Color.TRANSPARENT
                textFill = Color.WHITE
            }
        }
        val projects = model.getRecentProjects()
        listview<Project> {
            val listCellHeight = 50.0
            cellFormat {
                style {
                    backgroundColor += c("#3e3e44")
                    textFill = Color.WHITE
                    alignment = Pos.CENTER
                    maxHeight = Dimension(listCellHeight, Dimension.LinearUnits.px)
                    minHeight = Dimension(listCellHeight, Dimension.LinearUnits.px)
                }

                text = it.name
            }
            style {
                backgroundColor += c("#3e3e44")
                maxHeight = Dimension(listCellHeight * projects.size, Dimension.LinearUnits.px)
            }


            for (project in projects)
                items.add(project)
            selectionModel.selectionMode = SelectionMode.SINGLE
            onUserSelect {
                MenuView(it.path).openWindow()
            }
        }
    }
}