package views.projectChoser

import javafx.scene.control.Button
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Priority
import models.Project
import tornadofx.hgrow
import views.MenuView

class ProjectButtonView(private val project: Project) : Button() {
    init {
        this.text = project.name
        addEventHandler(MouseEvent.MOUSE_CLICKED) {
            MenuView(project.path).openWindow()
        }
        hgrow = Priority.ALWAYS
    }
}