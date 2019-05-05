package views

import controllers.SyntaxAnalyzerImpl
import javafx.scene.control.TextArea
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCombination
import javafx.scene.input.KeyEvent
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import models.OutputConsoleToView
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
    private val outputerConsoleToView: OutputConsoleToView by inject()

    val codeArea = CodeArea()


    init {
        importStylesheet(EditorStyles::class)
    }

    private fun analyze() {
        toDefaultColor()
        val errors = syntaxAnalyzer.analyze(codeArea.text)
        outputerConsoleToView.clear()

        errors.forEach { token ->
            val styleClasses = Arrays.asList("test")
            outputerConsoleToView.println("Line=${token.paragraph} start=${token.startPosition} end=${token.endPosition}")
            println("Line=${token.paragraph} start=${token.startPosition} end=${token.endPosition}")

            //safe errors
            if (token.endPosition!! < codeArea.paragraphs[token.paragraph!!].length()) {
                codeArea.setStyle(token.paragraph!!,
                        token.startPosition!!,
                        token.endPosition!! + 1, styleClasses)
            }
        }
    }

    override val root: VBox = vbox {
        hgrow = Priority.ALWAYS
        vgrow = Priority.ALWAYS
        codeArea.replaceText(0, 0, loadText())

        loadSubscriptions()
        loadShortCut()
        analyze()
        codeArea.addEventHandler(KeyEvent.KEY_PRESSED) {
            analyze()
        }

        //TODO styles in other place
        codeArea.hgrow = Priority.ALWAYS
        codeArea.vgrow = Priority.ALWAYS

        codeArea.style {
            backgroundColor += ColorHolder.primaryColor
        }

        codeArea.stylesheet {
            Stylesheet.content {
                backgroundColor += ColorHolder.primaryColor
                borderWidth += box(Dimension(0.0, Dimension.LinearUnits.px))
                baseColor = ColorHolder.primaryColor
            }

            Stylesheet.text {
                fill = ColorHolder.fontColor
            }

            this.addSelection(CssSelection(CssSelector(CssRuleSet(CssRule.c("test")))) {
                fill = ColorHolder.errorColor
            })
            this.addSelection(CssSelection(CssSelector(CssRuleSet(CssRule.c("default")))) {
                fill = ColorHolder.fontColor
            })
        }
        add(codeArea) //TODO this wrong example of adding
    }

    /**
     * Reset all colors to default
     */
    private fun toDefaultColor() {
        for (i in 0 until codeArea.paragraphs.size) {
            codeArea.setStyle(i, 0, codeArea.paragraphs[i].length(), Arrays.asList("default"))
        }
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