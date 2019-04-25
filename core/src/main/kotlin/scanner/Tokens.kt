package scanner

/**
 * @author kostya05983
 *
 * (1) single_input -> NEWLINE | simple_stmt | compound_stmt NEWLINE
 * compound_stmt -> if_stmt
 * (2) if_stmt -> ‘if’ or_test : suite | ‘if’ test ‘:’ suite ‘else’ ‘:’ suite | ‘if’ test ‘:’ suite K
 * (3) K -> 'elif' or_test ':' suite K | 'elif' or_test ':' suite | 'elif' or_test ':' suite ‘else’ ‘:’ suite
 * (4) suite-> simple_stmt | NEWLINE INDENT | DEDENT
 * (6) L-> stmt | stmt L
 * (7) stmt -> simple_stmt | compound_stmt
 * (8) or_test -> and_test | P
 * (9) P -> ‘or’ and_test | ‘or’ and_test P
 * (10) and_test -> not_test | J
 * (11) J -> ‘and’ not_test | ‘and’ not_test J
 * (12) not_test -> ‘not’ not_test | comparison
 * (13) comparison -> expr T | expr
 * (14) T -> comp_op expr | comp_op expr T
 * (15) comp_op -> '<' | '>' | '==' | '>=' | '<=' | '<>' | '!='
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
enum class Tokens(var literal: String) {
    NEWLINE("\n"),
    IF("if"),
    COLON(":"),
    ELSE("else"),
    ELIF("elif"),
    INDENT("\t"),
    OR("or"),
    AND("and"),
    AND_DOUBLE("&&"),
    NOT("not"),
    LESS("<"),
    MORE(">"),
    EQUAL("=="),
    MORE_EQUAL(">="),
    LESS_EQUAL("<="),
    NOT_EQUAL("<>"),
    NOT_EQUAL_C("!="),
    SLASH("|"),
    SLASH_DOUBLE("||"),
    CAP("^"),
    BINARY_AND("&"),
    SHIFT_LEFT("<<"),
    SHIFT_RIGHT(">>"),
    PLUS("+"),
    MINUS("-"),
    MULTIPLE("*"),
    DIVIDE("/"),
    REMAINDER("%"),
    FLOOR_DIVISION("//"),
    INVERSE("~"),
    TRUE("True"),
    FALSE("False"),
    SPACE(" "),
    LEFT_BRACKET("("),
    RIGHT_BRACKET(")"),
    IDENTIFIER(""),
    ASSIGNMENT("="),
    SIMPLE_STMT("simple_stmt")
}