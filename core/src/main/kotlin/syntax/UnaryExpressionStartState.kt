package syntax

import scanner.Token
import scanner.Tokens
import java.util.*

class UnaryExpressionStartState(override val analyzer: SyntaxAnalizer,
                                override val memory: Stack<Tokens>) : State {
    override fun analyze(token: Token) {
        when (token.token) {
            Tokens.NOT -> {
                analyzer.changeState(NotState(analyzer, memory))
            }
            Tokens.IDENTIFIER -> {
                analyzer.changeState(BinaryExpressionState(analyzer, memory))
            }
//            Tokens.LEFT_BRACKET -> {
//
//            }
            Tokens.PLUS -> {
                analyzer.changeState(PlusState(analyzer, memory))
            }
            Tokens.MINUS -> {
                analyzer.changeState(MinusState(analyzer, memory))
            }
            Tokens.INVERSE -> {
                analyzer.changeState(InverseState(analyzer, memory))
            }
            Tokens.TRUE -> {
                analyzer.changeState(BinaryExpressionState(analyzer, memory))
            }
            Tokens.FALSE -> {
                analyzer.changeState(BinaryExpressionState(analyzer, memory))
            }
            else -> {
                TODO("ERROR!")
            }
        }
        memory.push(token.token)
    }
}