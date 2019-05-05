package syntax

import scanner.Token
import scanner.Tokens
import java.util.*

class UnaryExpressionStartState(override val analyzer: SyntaxAnalyzer,
                                override val memory: Stack<Tokens>,
                                override val errorTokens: MutableList<Token>) : State {
    override fun analyze(token: Token) {
        when (token.token) {
            Tokens.NOT -> {
                analyzer.changeState(NotState(analyzer, memory, errorTokens))
            }
            Tokens.IDENTIFIER -> {
                analyzer.changeState(BinaryExpressionState(analyzer, memory, errorTokens))
            }
            Tokens.PLUS -> {
                analyzer.changeState(PlusState(analyzer, memory, errorTokens))
            }
            Tokens.MINUS -> {
                analyzer.changeState(MinusState(analyzer, memory, errorTokens))
            }
            Tokens.INVERSE -> {
                analyzer.changeState(InverseState(analyzer, memory, errorTokens))
            }
            Tokens.TRUE -> {
                analyzer.changeState(BinaryExpressionState(analyzer, memory, errorTokens))
            }
            Tokens.FALSE -> {
                analyzer.changeState(BinaryExpressionState(analyzer, memory, errorTokens))
            }
            else -> {
                errorTokens.add(token)
                //Neutralization
                analyzer.changeState(MainState(analyzer, memory, errorTokens))
            }
        }
        memory.push(token.token)
    }
}