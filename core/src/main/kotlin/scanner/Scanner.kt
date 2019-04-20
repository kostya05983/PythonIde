package scanner

import java.security.AlgorithmParameterGenerator

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
     *
     */
    fun scan(s: String): Array<Tokens> {
        val lines = s.split(Tokens.NEWLINE.literal)

        for (line in lines) {
            when (line) {
                Tokens.IF.literal -> {

                }
                Tokens.COLON.literal -> {

                }
                Tokens.ELSE.literal -> {

                }
                Tokens.ELIF.literal -> {

                }
                Tokens.INDENT.literal -> {

                }
                Tokens.OR.literal -> {

                }
                Tokens.AND.literal -> {

                }
                Tokens.NOT.literal -> {

                }
                Tokens.LESS.literal -> {

                }
                Tokens.MORE.literal -> {

                }
                Tokens.EQUAL.literal -> {

                }
                Tokens.MORE_EQUAL.literal -> {

                }
                Tokens.LESS_EQUAL.literal -> {

                }
                Tokens.NOT_EQUAL.literal -> {

                }
                Tokens.NOT_EQUAL_C.literal -> {

                }
                Tokens.SLASH.literal -> {

                }
                Tokens.CAP.literal -> {

                }
                Tokens.BINARY_AND.literal -> {

                }
                Tokens.SHIFT_LEFT.literal -> {

                }
                Tokens.SHIFT_RIGHT.literal -> {

                }
                Tokens.PLUS.literal -> {

                }
                Tokens.MINUS.literal -> {

                }
                Tokens.MULTIPLE.literal -> {

                }
                Tokens.DIVIDE.literal -> {

                }
                Tokens.REMAINDER.literal -> {

                }
                Tokens.FLOOR_DIVISION.literal -> {

                }
                Tokens.INVERSE.literal -> {

                }
                Tokens.TRUE.literal -> {

                }
                Tokens.FALSE.literal -> {

                }
                Tokens.SPACE.literal -> {

                }
            }
        }

        return arrayOf()
    }

    /**
     * Delete spaces in string
     * @param s - string where we need to delete spaces
     * @return - string without spaces
     */
    private fun deleteAllSpaces(s: String): String {
        val sb = StringBuilder()
        for (ch in s) {
            if (ch != ' ')
                sb.append(ch)
        }
        return sb.toString()
    }

    /**
     * First we delete spaces
     * next we parse arithmetic expression
     * k==2:
     */
    private fun scanAriphemetic(s: String) {
        val strWithoutSpaces = deleteAllSpaces(s)
        //todo divide by space, and than we will determine
        for (i in 0 until strWithoutSpaces.length) {
            when {
                strWithoutSpaces[i] == Alphabet.DIVIDER.ch -> {
                    if (i+1!=strWithoutSpaces.length&& strWithoutSpaces[i+1] == Alphabet.DIVIDER.ch) {

                    } else {
                        //TODO alerting
                    }
                }
                strWithoutSpaces[i] == Alphabet.REMAINDER.ch -> {

                }
                strWithoutSpaces[i] == Alphabet.A.ch -> {

                }
                strWithoutSpaces[i] == Alphabet.MINUS.ch -> {

                }
                strWithoutSpaces[i] == Alphabet.PLUS.ch -> {

                }
                strWithoutSpaces[i] == Alphabet.EQUAL.ch -> {

                }
                strWithoutSpaces[i] == Alphabet.NOT_EQUAL.ch -> {

                }
                strWithoutSpaces[i]== Alphabet.N.ch -> {

                }
                strWithoutSpaces[i] == Alphabet.INVERSE.ch -> {

                }
                strWithoutSpaces[i] == Alphabet.LEFT_SHIFT.ch -> {

                }
                strWithoutSpaces[i] == Alphabet.RIGHT_SHIFT.ch -> {

                }
                strWithoutSpaces[i] == Alphabet.O.ch -> {

                }
            }
        }
    }


}