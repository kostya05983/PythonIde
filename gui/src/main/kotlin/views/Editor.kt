package views

import controllers.SyntaxAnalyzerImpl
import javafx.scene.control.TextArea
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCombination
import javafx.scene.input.KeyEvent
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import org.fxmisc.richtext.CodeArea
import styles.EditorStyles
import tornadofx.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import java.util.*
import java.util.stream.Collectors

class Editor : Fragment() {
    val path: String by param()

    private val syntaxAnalyzer: SyntaxAnalyzerImpl by inject()

    public val codeArea = CodeArea()

    init {
        importStylesheet(EditorStyles::class)
    }

    //    override val root: CodeArea = CodeArea()
    override val root: VBox = vbox {
        hgrow = Priority.ALWAYS
        vgrow = Priority.ALWAYS
        codeArea.replaceText(0, 0, loadText())

        loadSubscriptions()
        loadShortCut()
        codeArea.addEventHandler(KeyEvent.KEY_PRESSED) {
            if (it.code == KeyCode.ENTER) {
                println("Enter")
                syntaxAnalyzer.analyze(codeArea.text)

                val styleClasses = Arrays.asList("test")

                codeArea.setStyle(1,0, 10, styleClasses)
            }
        }

        codeArea.stylesheet {
            Stylesheet.content {
                backgroundColor += ColorHolder.primaryColor
                borderWidth += box(Dimension(0.0, Dimension.LinearUnits.px))
            }
            Stylesheet.focused {
                backgroundColor += ColorHolder.primaryColor
            }
            Stylesheet.text {
                fill = ColorHolder.fontColor
            }

            this.addSelection(CssSelection(CssSelector(CssRuleSet(CssRule.c("test")))) {
                fill = ColorHolder.errorColor
            })
        }
        add(codeArea) //TODO this wrong example of adding
    }

    private fun loadSubscriptions() {
        subscribe<WriteEvent> {
            writeTextToFile(codeArea.text)
        }
    }

    private fun loadShortCut() {
        shortcut(KeyCombination.valueOf("Ctrl+S")) {
            writeTextToFile(codeArea.text)
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