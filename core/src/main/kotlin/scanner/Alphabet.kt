package scanner

/**
 * this enum class contains alphabet of python language
 * it is used in scanner for identifies language's symbols
 * and transfer them in tokens for syntax analyzer
 */
enum class Alphabet(val ch: Char) {
    PLUS('+'),
    MINUS('-'),
    LEFT_SHIFT('<'),
    RIGHT_SHIFT('>'),
    EQUAL('='),
    NOT_EQUAL('!'),
    REMAINDER('%'),
    DIVIDER('/'),
    MULTIPLE('*'),
    INVERSE('~'),
    O('o'),
    A('a'),
    N('n'),
    LEFT_BRACKET('('),
    RIGHT_BRACKET(')'),
    AND('&'),
    OR('|'),

    /**
     * Next there are delimiters or end symbols,
     * which can't be start symbol of double liters or
     * three time liters
     */
    D('d'),
    SPACE(' '),
    R('r'),
    T('t')
}