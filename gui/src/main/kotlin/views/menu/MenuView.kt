package views.menu

import javafx.scene.Parent
import javafx.stage.StageStyle
import styles.menu.MenuViewStyles
import tornadofx.*
import views.*
import views.course.WindowWithText
import views.projectChoser.ProjectChooser
import views.projectStructure.EnterFileNamePopUp
import views.projectStructure.OpenEvent

class MenuView : View() {
    private val editorTabPane: EditorTabPane by inject()

    init {
        importStylesheet(MenuViewStyles::class)
    }

    override val root: Parent = menubar {
        menu("File") {
            item("New")
            item("Open") {
                action {
                    val file = chooseDirectory { }
                    if (file != null)
                        fire(OpenEvent(file))
                }
            }
            item("Open Recent")
            item("Close project") {
                action {
                    fire(MyEvent())
                }
            }
            item("Project Structure")
            item("Save") {
                action {
                    fire(WriteEvent())
                }
            }
            item("Save all")
            item("Exit") {
                action {
                    close()
                }
            }
        }
        menu("Editor") {
            item("Undo", "Shortcut+Z") {

                action {
                    editorTabPane.currentEditor?.codeArea?.undo()
                }
            }
            item("Redo", "Shortcut+Shift+Z") {
                action {
                    editorTabPane.currentEditor?.codeArea?.redo()
                }
            }
            item("Cut", "Shortcut+X") {
                action {
                    editorTabPane.currentEditor?.codeArea?.undo()
                }
            }
            item("Copy", "Shortcut+C") {
                action {
                    editorTabPane.currentEditor?.codeArea?.copy()
                }
            }
            item("Paste", "Shortcut+V") {
                action {
                    editorTabPane.currentEditor?.codeArea?.paste()
                }
            }
            item("Delete", "Shortcut+Y") {
                action {
                    TODO("delete selected text")
//                    editorTabPane.currentEditor?.codeArea?.deleteSelectedText()
                }
            }
            item("Select all", "Shortcut+A") {
                action {
                    editorTabPane.currentEditor?.codeArea?.selectAll()
                }
            }
        }
        menu("View") {
            item("Tool")
            item("Project Bar")
            //e.t.c
        }
        menu("Code") {
            item("Reformat Code")
            //e.t.c
        }
        menu("Analyze") {
            item("Code cleanup")
            item("Inspections")
        }
        menu("Refactor") {
            item("Rename")
            //e.t.c
        }
        menu("Build") {
            item("Build Project")
            item("Rebuild Project")
            item("Build Module Apk")
        }
        menu("Run") {
            item("Run")
            item("Debug")
            //e.t.c
        }
        menu("Tools") {
            //e.t.c
        }
        menu("Vcs") {
            //git,  .e.t.c
        }
        menu("Window") {
            item("Restore Default Layout")
        }
        menu("Help") {
            item("Find Action")
            item("Help")
            item("About")
            item("Task").action {
                val task = """
                    Разработать парсер упрощенного арифметического
                    оператора условного перехода языка PYTHON 3.7.2
                    В операторе могут использоваться операции из побитовой логики &,|,<<,>>.
                    Арифметическое выражение с целью упрощение представлено без скобок,
                    содержит в себе операции *,//,%,-,+.
                """.trimIndent()
                val window = WindowWithText(task)
                window.title = "Grammar"
                window.openModal(stageStyle = StageStyle.UTILITY)
            }
            item("Grammar").action {
                val grammar = """
                    (1) single_input -> NEWLINE | simple_stmt | compound_stmt NEWLINE
                    (2) compound_stmt -> if_stmt
                    (3) if_stmt -> ‘if’ or_test ‘:’ suite IA
                    (4) IA -> [‘else’ ‘:’ suite] | ‘elif’ or_test ‘:’ suite QA [‘else’ ‘:’ suite]
                    (5) QA ->  | (‘elif’ or_test ‘:’ suite) QA
                    (6) suite-> simple_stmt | NEWLINE INDENT L DEDENT
                    (7) L-> stmt | stmt L
                    (8) stmt -> simple_stmt | compound_stmt
                    (9) or_test -> and_test P
                    (10) P -> | ‘or’ and_test P
                    (11) and_test -> not_test J
                    (12) J -> | ‘and’ not_test J
                    (13) not_test -> ‘not’ not_test | comparison
                    (14) comparison -> expr T
                    (15) T ->  | comp_op expr T
                    (16) comp_op -> '<' | '>' | '==' | '>=' | '<=' | '<>' | '!='
                    (17) expr -> xor_expr G
                    (18) G ->  | ‘|’ xor_expr G
                    (19) xor_expr -> and_expr I
                    (20) I -> | ‘^’ and_expr I
                    (21) and_expr -> shift_expr H
                    (22) H ->  | ‘&’ shift_expr H
                    (23) shift_expr -> arith_expr F
                    (24) F -> | Y arith_expr F
                    (25) Y -> ‘<<’ | ‘>>’
                    (26) arith_expr -> term A
                    (27) A-> | J term A
                    (28) J -> ‘+’ | ’-’
                    (29) term -> factor U
                    (30) U-> | F factor U
                    (31) F -> '*' | '/' | '%' | '//'
                    (32) factor -> R factor | atom_expr
                    (33) R -> '+' | '-' | '~'
                    (34) atom_expr -> NUMBER | STRING | 'True' | 'False'
                """.trimIndent()
                val window = WindowWithText(grammar)
                window.title = "Grammar"
                window.openModal(stageStyle = StageStyle.UTILITY)
            }
            item("Classification").action {
                val text = """
                    Данная грамматика , согласно классификации Хомского,
                    является контекстно-свободной, так как правая часть
                    каждой продукции начинается либо с терминального символа,
                    либо с нетерминального.
                """.trimIndent()
                val window = WindowWithText(text)
                window.title = "Classification"
                window.openModal(stageStyle = StageStyle.UTILITY)
            }
            item("Method of analysis").action {
                val text = """
                    Автомат с магазинной памятью
                """.trimIndent()
                val window = WindowWithText(text)
                window.title = "Method of analysis"
                window.openModal(stageStyle = StageStyle.UTILITY)
            }
            item("Diagnostic and neutralization of errors").action {
                val text = """
                    Для данной грамматики производится только диагности
                    и нейтрализация ошибок.
                    Нейтрализация ошибок осуществляется по методу Айронса,
                    то есть, спускаясь по синтаксическому дереву без возврата
                    по контексту, при обнаружении тупиковой ситуации отбрасываются
                    те литеры (символы), которые привели в тупиковую ситуацию
                    и разбор продолжается.
                """.trimIndent()
                val window = WindowWithText(text)
                window.title = "Diagnostic and neutralization of errors"
                window.openModal(stageStyle = StageStyle.UTILITY)
            }
            item("Literature").action {
                val text = """
                    Теория и практика языковых процессов Шорников.Ю.В
                """.trimIndent()
                val window = WindowWithText(text)
                window.title = "Diagnostic and neutralization of errors"
                window.openModal(stageStyle = StageStyle.UTILITY)
            }
        }
    }

}