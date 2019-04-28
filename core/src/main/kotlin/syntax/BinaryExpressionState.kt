package syntax

import scanner.Token
import scanner.Tokens
import java.util.*

/**
 * We can go from here to end state
 * @author kostya05983
 */
class BinaryExpressionState(override val analyzer: SyntaxAnalyzer,
                            override val memory: Stack<Tokens>) : State {

    override fun analyze(token: Token) {
        when (token.token) {
            Tokens.COLON -> {
                analyzer.changeState(IndentState(analyzer, memory))
                //With if all
            }
            Tokens.OR -> {
                analyzer.changeState(UnaryExpressionStartState(analyzer, memory))
            }
            Tokens.AND -> {
                analyzer.changeState(UnaryExpressionStartState(analyzer, memory))
            }
            Tokens.AND_DOUBLE -> {
                analyzer.changeState(UnaryExpressionStartState(analyzer, memory))
            }
            Tokens.MORE -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory))
            }
            Tokens.EQUAL -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory))
            }

            Tokens.LESS -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory))
            }
            Tokens.MORE_EQUAL -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory))
            }
            Tokens.LESS_EQUAL -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory))
            }
            Tokens.NOT_EQUAL_C -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory))
            }
            Tokens.SLASH -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory))
            }
            Tokens.BINARY_AND -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory))
            }
            Tokens.SHIFT_LEFT -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory))
            }
            Tokens.SHIFT_RIGHT -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory))
            }
            Tokens.PLUS -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory))
            }
            Tokens.MINUS -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory))
            }
            Tokens.MULTIPLE -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory))
            }
            Tokens.DIVIDE -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory))
            }
            Tokens.REMAINDER -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory))
            }
            Tokens.FLOOR_DIVISION -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory))
            }
            else -> {
                TODO("ERROR Binary expression state")
            }
        }
        memory.push(token.token)
    }
}