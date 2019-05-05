package syntax

import scanner.Token
import scanner.Tokens
import java.util.*

class UnaryExpressionEndState(override val analyzer: SyntaxAnalyzer,
                              override val memory: Stack<Tokens>,
                              override val errorTokens: MutableList<Token>) : State {

    private val lastMatchTokens = arrayOf(
            Tokens.AND,
            Tokens.OR,
            Tokens.BINARY_AND,
            Tokens.SLASH,
            Tokens.SHIFT_LEFT,
            Tokens.SHIFT_RIGHT,
            Tokens.LESS,
            Tokens.MORE,
            Tokens.EQUAL,
            Tokens.MORE_EQUAL,
            Tokens.LESS_EQUAL,
            Tokens.NOT_EQUAL_C,
            Tokens.PLUS,
            Tokens.MINUS,
            Tokens.MULTIPLE,
            Tokens.DIVIDE,
            Tokens.REMAINDER,
            Tokens.FLOOR_DIVISION
    )

    override fun analyze(token: Token) {
        when {
            token.token == Tokens.PLUS -> {
                analyzer.changeState(PlusState(analyzer, memory, errorTokens))
            }
            token.token == Tokens.MINUS -> {
                analyzer.changeState(MinusState(analyzer, memory, errorTokens))
            }
            token.token == Tokens.INVERSE -> {
                analyzer.changeState(InverseState(analyzer, memory, errorTokens))
            }
            token.token == Tokens.NOT -> {
                analyzer.changeState(NotState(analyzer, memory, errorTokens))
            }
            token.token == Tokens.IDENTIFIER -> {
                analyzer.changeState(BinaryExpressionState(analyzer, memory, errorTokens))
            }
            token.token == Tokens.TRUE -> {
                analyzer.changeState(BinaryExpressionState(analyzer, memory, errorTokens))
            }
            token.token == Tokens.FALSE -> {
                analyzer.changeState(BinaryExpressionState(analyzer, memory, errorTokens))
            }
            !lastMatchTokens.contains(memory.peek()) -> {
            }
            else -> {
                errorTokens.add(token)
                //neutralization
                analyzer.changeState(MainState(analyzer, memory, errorTokens))
            }
        }
        memory.push(token.token)
    }
}