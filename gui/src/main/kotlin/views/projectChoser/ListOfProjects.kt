package views.projectChoser

import ColorHolder
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.control.ScrollPane
import javafx.scene.control.SelectionMode
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import models.ListOfProjectModel
import models.Project
import styles.projectChoser.ListOfProjectsStyle
import tornadofx.*
import tornadofx.Stylesheet.Companion.cellSelection
import tornadofx.Stylesheet.Companion.decrementArrow
import tornadofx.Stylesheet.Companion.decrementButton
import tornadofx.Stylesheet.Companion.focused
import tornadofx.Stylesheet.Companion.hover
import tornadofx.Stylesheet.Companion.incrementArrow
import tornadofx.Stylesheet.Companion.incrementButton
import tornadofx.Stylesheet.Companion.listCell
import tornadofx.Stylesheet.Companion.rowSelection
import tornadofx.Stylesheet.Companion.selected
import tornadofx.Stylesheet.Companion.thumb
import tornadofx.Stylesheet.Companion.vertical
import views.MenuView

class ListOfProjects : View() {
    override val root: Parent = VBox()
    private val model = ListOfProjectModel()

    init {
        importStylesheet(ListOfProjectsStyle::class)
        with(root) {
            scrollpane {
                hbarPolicy = ScrollPane.ScrollBarPolicy.NEVER
                vgrow = Priority.ALWAYS
                add(projectListView())
            }
        }
    }

    private fun projectListView() = listview<Project> {
        cellFormat {
            style {
                textFill = Color.WHITE
                alignment = Pos.CENTER_LEFT
            }
            stylesheet {
                hover {
                    backgroundColor += ColorHolder.selectionColor
                }
            }
            text = """
                        ${it.name}
                        ${it.path}
                    """.trimIndent()
        }

        val projects = model.getRecentProjects()
        for (project in projects)
            items.add(project)
        selectionModel.selectionMode = SelectionMode.SINGLE
        onUserSelect(1) {
            find(ProjectChooser::class).replaceWith(MenuView(it.path), sizeToScene = true)
        }
        style {
            backgroundColor += Color.TRANSPARENT

            fitToHeight = true

            vBarPolicy = ScrollPane.ScrollBarPolicy.ALWAYS

        }
        vgrow = Priority.ALWAYS

        stylesheet {
            rowSelection {
                backgroundColor += ColorHolder.selectionColor
                baseColor = ColorHolder.selectionColor
                focusColor = ColorHolder.selectionColor
            }
        }
    }
}
