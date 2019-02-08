package views

import javafx.scene.control.TextArea

class Editor: TextArea() {

    fun deleteSelectedText() {
        text.replace(selectedText, "")
    }
}