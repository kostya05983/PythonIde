package scanner

import scanner.ifStates.ConditionState
import java.util.*

/**
 * @author kostya05983
 *
 * (1) single_input -> NEWLINE | simple_stmt | compound_stmt NEWLINE
 * (2) compound_stmt -> if_stmt
 * (3) if_stmt -> ‘if’ or_test : suite | ‘if’ test ‘:’ suite ‘else’ ‘:’ suite | ‘if’ test ‘:’ suite K
 * (4) K -> 'elif' or_test ':' suite K | 'elif' or_test ':' suite | 'elif' or_test ':' suite ‘else’ ‘:’ suite
 * (5) suite-> simple_stmt | NEWLINE INDENT L DEDENT
 * (6) L-> stmt | stmt L
 * (7) stmt -> simple_stmt | compound_stmt
 * (8) or_test -> and_test | P
 * (9) P -> ‘or’ and_test | ‘or’ and_test P
 * (10) and_test -> not_test | J
 * (11) J -> ‘and’ not_test | ‘and’ not_test J
 * (12) not_test -> ‘not’ not_test | comparison
 * (13) comparison -> expr T | expr
 * (14) T -> comp_op expr | comp_op expr T
 * (15) comp_op -> '<' | '>' | '==' | '>=' | '<=' | '!='
 * (16) expr -> xor_expr | xor_expr G
 * (17) G -> ‘|’ xor_expr G | ‘|’ xor_expr
 * (18) xor_expr -> and_expr | and_expr I
 * (19) I -> ‘^’ and_expr | ‘^’ and_expr I
 * (20) and_expr -> shift_expr | shift_expr H
 * (21) H -> ‘&’ shift_expr | ‘&’ shift_expr H
 * (22) shift_expr -> arith_expr | arith_expr F
 * (23) F -> Y arith_expr | Y arith_expr F
 * (24) Y -> ‘<<’ | ‘>>’
 * (25) arith_expr -> term | term A
 * (26) A-> J term | J term A
 * (27) J -> ‘+’ | ’-’
 * (28) term -> factor | factor U
 * (29) U ->  F factor |  F factor U
 * (30) F -> '*' | '/' | '%' | '//'
 * (31) factor -> R factor | atom_expr
 * (32) R -> '+' | '-' | '~'
 * (33) atom_expr -> NUMBER | STRING | 'True' | 'False'
 */
class ScannerAutomate {
    private var currentState: State = ConditionState(this, LinkedList(), Stack(), 0, 0)

    fun scan(s: String): List<Token> {
        val result = mutableListOf<Token>()
        for (char in s) {
            currentState.parse(char)
            currentState.offset++
        }
        result.addAll(currentState.tokensArray)

        if (currentState.memory.isNotEmpty()) {
            if (currentState !is ConditionState)
                result.addAll(joinToIdentifierWithIndent(currentState.memory, currentState.offset, currentState.page))
            else result.addAll(joinToSimpleStatementWithIndent(currentState.memory, currentState.offset, currentState.page))
        }

        return result.filter { it.token != Tokens.EMPTY_TOKEN }
    }

    fun changeState(newState: State) {
        currentState = newState
    }

    fun reset() {
        currentState = ConditionState(this, LinkedList(), Stack(), 0, 0)
    }

    private fun joinToIdentifierWithIndent(memory: Stack<Char>, offset: Int, paragraph: Int): List<Token> {
        val sb = StringBuilder()
        val list = mutableListOf<Token>()

        var c = false
        var amountSpace = 0
        for (ch in memory) {
            sb.append(ch)
            if (ch != ' ') {
                c = true
                amountSpace = 0
            } else {
                amountSpace++
            }
            if (amountSpace == 4 && !c) {
                list.add(Token(Tokens.INDENT, Tokens.INDENT.literal))
                amountSpace = 0
            }
        }

        if (c) {
            val token = Token(Tokens.IDENTIFIER, sb.toString())
            token.paragraph = paragraph
            token.startPosition = offset - sb.toString().length
            token.endPosition = offset-1
            memory.clear()
            list.add(token)
        } else {
            list.add(Token(Tokens.EMPTY_TOKEN, ""))
        }
        return list
    }

    fun joinToIdentifier(memory: Stack<Char>, offset: Int, paragraph: Int): Token {
        val sb = StringBuilder()

        var c = false
        for (ch in memory) {
            sb.append(ch)
            if (ch != ' ') {
                c = true
            }
        }

        return if (c) {
            val token = Token(Tokens.IDENTIFIER, memory, offset, paragraph)
            memory.clear()
            token
        } else {
            Token(Tokens.EMPTY_TOKEN, "")
        }
    }

    fun joinToSimpleStatementWithIndent(memory: Stack<Char>, offset: Int, paragraph: Int): List<Token> {
        val sb = StringBuilder()
        val list = mutableListOf<Token>()

        var c = false
        var amountSpace = 0
        var indentOffset = 0
        for (ch in memory) {
            sb.append(ch)
            if (ch != ' ') {
                c = true
                amountSpace = 0
            } else {
                amountSpace++
            }
            if (amountSpace == 4 && !c) {
                val token = Token(Tokens.INDENT, Tokens.INDENT.literal)
                token.paragraph = paragraph
                token.startPosition = indentOffset - sb.toString().length + 1
                token.endPosition = indentOffset
                list.add(token)

                amountSpace = 0
                sb.clear()
            }
            indentOffset++
        }

        if (c) {
            val token = Token(Tokens.SIMPLE_STMT, sb.toString())
            token.startPosition = offset - sb.toString().length
            token.endPosition = offset - 1
            token.paragraph = paragraph
            memory.clear()
            list.add(token)
        } else {
            list.add(Token(Tokens.EMPTY_TOKEN, ""))
        }
        return list
    }
}
