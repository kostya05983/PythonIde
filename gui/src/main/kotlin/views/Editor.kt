package views

import controllers.Lab2AutomatAnalyzer
import controllers.Lab3RegexAnalyzer
import javafx.scene.control.TextArea
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCombination
import javafx.scene.input.KeyEvent
import javafx.scene.layout.Priority
import styles.EditorStyles
import tornadofx.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import java.util.stream.Collectors

class Editor : Fragment() {
    val path: String by param()

    val syntaxAnylyzer: Lab2AutomatAnalyzer by inject()

    val regexAnalyzer: Lab3RegexAnalyzer by inject()

    init {
        importStylesheet(EditorStyles::class)
    }

    override val root: TextArea = textarea {
        hgrow = Priority.ALWAYS
        vgrow = Priority.ALWAYS

        text = loadText()

        loadSubscriptions()
        loadShortCut()

        addEventHandler(KeyEvent.KEY_PRESSED) {
            if (it.code == KeyCode.ENTER) {
//                syntaxAnylyzer.analyze(text)
                regexAnalyzer.analyze(text)
            }
        }
    }

    private fun loadSubscriptions() {
        subscribe<WriteEvent> {
            writeTextToFile(root.text)
        }
    }

    private fun loadShortCut() {
        shortcut(KeyCombination.valueOf("Ctrl+S")) {
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