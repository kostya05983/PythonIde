package views

import ColorHolder
import javafx.scene.control.TextArea
import javafx.scene.layout.Priority
import tornadofx.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import java.util.stream.Collectors

class Editor() : Fragment() {
    val path: String by param()


    override val root: TextArea = textarea {
        stylesheet {
            Stylesheet.content {
                backgroundColor += ColorHolder.primaryColor
                borderWidth += box(Dimension(0.0, Dimension.LinearUnits.px))
            }
            Stylesheet.focused {
                backgroundColor += ColorHolder.primaryColor
            }
        }

        style {
            backgroundInsets += box(Dimension(0.0, Dimension.LinearUnits.px))
            baseColor = ColorHolder.primaryColor
            focusColor = ColorHolder.primaryColor
            textFill = ColorHolder.fontColor
            hgrow = Priority.ALWAYS
            vgrow = Priority.ALWAYS
        }

        text = loadText()

        loadSubscriptions()
    }

    private fun loadSubscriptions() {
        subscribe<WriteEvent> {
            println("")
            writeTextToFile(root.text)
        }
    }

    //TODO interface for loading and and writing in seperate class
    //TODO when need to make higlighting process stream for highlight
    private fun loadText(): String {
        val result: String
        val br = BufferedReader(FileReader(path))
        result = br.lines().collect(Collectors.toList()).joinToString("\n")
        br.close()
        return result
    }

    private fun writeTextToFile(text: String) {
        val br = BufferedWriter(FileWriter(path, false))
        br.write(text)
        br.flush()
        br.close()
    }
}

class WriteEvent : FXEvent()

fun TextArea.deleteSelectedText() {
    text.replace(selectedText, "")
}