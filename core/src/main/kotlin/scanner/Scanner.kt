package scanner

import java.security.AlgorithmParameterGenerator
import java.util.*
import scanner.Tokens
import scanner.ariphmeticScanAutomat.ArithmeticScanner
import scanner.statementAutomat.StatementScanner

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
class Scanner {

    /**
     * Examples of language:
     * if k==2:
     *  k=4
     * if k==4:
     *  k=5
     * else:
     *  k=6
     *
     * if k==8:
     *  k=6
     * elif k==9:
     *  k=10
     * else:
     *  k=18
     * if line starts with if, we scan it as if
     * if line starts with when we scan it as when
     * and so on
     */
    fun scan(s: String): List<Token> {
        val lines = s.split(Tokens.NEWLINE.literal)

        val tokens = ArrayList<Token>()

        for (i in 0 until lines.size) {
            tokens.addAll(findIndent(lines[i]))
            val trailLine = lines[i].trim()
            when {
                trailLine.contains(Tokens.ELIF.literal) -> {
                    tokens.addAll(scanElseIf(trailLine))
                }
                trailLine.contains(Tokens.IF.literal) -> {
                    tokens.addAll(scanIf(trailLine))
                }
                trailLine.contains(Tokens.ELSE.literal) -> {
                    tokens.add(Token(Tokens.ELSE, Tokens.ELSE.literal))
                }
                else -> { // all others we think that is just statements
                    tokens.add(Token(Tokens.SIMPLE_STMT, trailLine))
                }
            }
            if (i != lines.size - 1)
                tokens.add(Token(Tokens.NEWLINE, Tokens.NEWLINE.literal))
        }
        return tokens
    }

    /**
     * Find all indents in line
     */
    private fun findIndent(str: String): List<Token> {
        val list = mutableListOf<Token>()
        var count = 0
        for (char in str) {
            if (char == ' ') {
                count++
            } else {
                count = 0
            }
            if (count == 4)
                list.add(Token(Tokens.INDENT, Tokens.INDENT.literal))
        }
        return list
    }

    /**
     * Scan if in rules of if expression
     */
    private fun scanIf(line: String): List<Token> {
        val list = LinkedList<Token>()
        if (line[line.length - 1] == ':') {
            list.add(Token(Tokens.IF, Tokens.IF.literal))
            val expression = line.substring(2, line.length - 1)
            val arithmeticScanner = ArithmeticScanner()
            val expressionTokens = arithmeticScanner.scan(expression)
            list.addAll(expressionTokens)
        } else {
            TODO("Error !!! ca we get here? only if we contain if, but we don't contain :")
        }
        return list
    }

    /**
     * Scan elif expression
     */
    private fun scanElseIf(line: String): List<Token> {
        val list = LinkedList<Token>()
        if (line[line.length - 1] == ':') {
            list.add(Token(Tokens.ELIF, Tokens.ELIF.literal))
            val expression = line.substring(4, line.length - 1)
            val arithmeticScanner = ArithmeticScanner()
            val expressionToken = arithmeticScanner.scan(expression)
            list.addAll(expressionToken)
        } else {
            TODO("Error !!! elif doesn't contain :")
        }
        return list
    }

    /**
     * example
     * k = 2
     * Test(2)
     * k = Test(2,4)
     * println(2)
     * identifierState -> if we meet assignment we add asignment and stay in identifierState if we meet ( we go next->
     * and scan arguments as identifiers before we meet )
     * for example line contains
     * println(it)
     * we have to scan it as simpleStatement,leftBracket,identifier,rightBracket, but how we understand
     * when identifier is function, class or something else
     * Just we go through lines in first time, and scan all as identifiers
     * we got Identifier, leftBracket, identifier, rightBracket
     * next we got throuth our tokens and if we seee identifier and then leftBracket to RightBracket
     * it is function or constructor, we will call it just simpleStatement
     */
    private fun scanStatementLine(line: String): List<Token> {
        val statementScanner = StatementScanner()
        return statementScanner.scan(line)
    }
}