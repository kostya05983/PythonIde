package syntax

import scanner.Token
import scanner.Tokens
import java.util.*

/**
 * We can go from here to end state
 * @author kostya05983
 */
class BinaryExpressionState(override val analyzer: SyntaxAnalyzer,
                            override val memory: Stack<Tokens>,
                            override val errorTokens: MutableList<Token>) : State {

    override fun analyze(token: Token) {
        when (token.token) {
            Tokens.COLON -> {
                analyzer.changeState(IndentState(analyzer, memory, errorTokens))
                //With if all
            }
            Tokens.OR -> {
                analyzer.changeState(UnaryExpressionStartState(analyzer, memory, errorTokens))
            }
            Tokens.AND -> {
                analyzer.changeState(UnaryExpressionStartState(analyzer, memory, errorTokens))
            }
            Tokens.AND_DOUBLE -> {
                analyzer.changeState(UnaryExpressionStartState(analyzer, memory, errorTokens))
            }
            Tokens.MORE -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory, errorTokens))
            }
            Tokens.EQUAL -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory, errorTokens))
            }
            Tokens.LESS -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory, errorTokens))
            }
            Tokens.MORE_EQUAL -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory, errorTokens))
            }
            Tokens.LESS_EQUAL -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory, errorTokens))
            }
            Tokens.NOT_EQUAL_C -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory, errorTokens))
            }
            Tokens.SLASH -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory, errorTokens))
            }
            Tokens.BINARY_AND -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory, errorTokens))
            }
            Tokens.SHIFT_LEFT -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory, errorTokens))
            }
            Tokens.SHIFT_RIGHT -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory, errorTokens))
            }
            Tokens.PLUS -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory, errorTokens))
            }
            Tokens.MINUS -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory, errorTokens))
            }
            Tokens.MULTIPLE -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory, errorTokens))
            }
            Tokens.DIVIDE -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory, errorTokens))
            }
            Tokens.REMAINDER -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory, errorTokens))
            }
            Tokens.FLOOR_DIVISION -> {
                analyzer.changeState(UnaryExpressionEndState(analyzer, memory, errorTokens))
            }
            else -> {
                errorTokens.add(token)
                analyzer.changeState(MainState(analyzer, memory, errorTokens))
            }
        }
        memory.push(token.token)
    }


}