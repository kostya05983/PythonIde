package scanner.ariphmeticScanAutomat

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import scanner.Token
import scanner.Tokens

internal class ArithmeticScannerTest {

    val test = """
        a ==5 and test==7<<2&238 and teta!=~(25>>34<<32)
    """.trimIndent()

    @org.junit.jupiter.api.Test
    fun scan() {
        val arithmeticScanner = ArithmeticScanner()
        val tokens = arithmeticScanner.scan(test)
        assertEquals(Token(Tokens.IDENTIFIER, "a"), tokens[0])
        assertEquals(Token(Tokens.EQUAL, Tokens.EQUAL.literal), tokens[1])
        assertEquals(Token(Tokens.IDENTIFIER, "5"), tokens[2])
        assertEquals(Token(Tokens.AND, Tokens.AND.literal), tokens[3])
        assertEquals(Token(Tokens.IDENTIFIER, "test"), tokens[4])
        assertEquals(Token(Tokens.EQUAL, Tokens.EQUAL.literal), tokens[5])
        assertEquals(Token(Tokens.IDENTIFIER, "7"), tokens[6])
        assertEquals(Token(Tokens.SHIFT_LEFT, Tokens.SHIFT_LEFT.literal), tokens[7])
        assertEquals(Token(Tokens.IDENTIFIER, "2"), tokens[8])
        assertEquals(Token(Tokens.BINARY_AND, Tokens.BINARY_AND.literal), tokens[9])
        assertEquals(Token(Tokens.IDENTIFIER, "238"), tokens[10])
        assertEquals(Token(Tokens.AND, Tokens.AND.literal), tokens[11])
        assertEquals(Token(Tokens.IDENTIFIER, "teta"), tokens[12])
        assertEquals(Token(Tokens.NOT_EQUAL_C, Tokens.NOT_EQUAL_C.literal), tokens[13])
        assertEquals(Token(Tokens.INVERSE, Tokens.INVERSE.literal), tokens[14])
        assertEquals(Token(Tokens.LEFT_BRACKET, Tokens.LEFT_BRACKET.literal), tokens[15])
        assertEquals(Token(Tokens.IDENTIFIER, "25"), tokens[16])
        assertEquals(Token(Tokens.SHIFT_RIGHT, Tokens.SHIFT_RIGHT.literal), tokens[17])
        assertEquals(Token(Tokens.IDENTIFIER, "34"), tokens[18])
        assertEquals(Token(Tokens.SHIFT_LEFT, Tokens.SHIFT_LEFT.literal), tokens[19])
        assertEquals(Token(Tokens.IDENTIFIER, "32"), tokens[20])
        assertEquals(Token(Tokens.RIGHT_BRACKET, Tokens.RIGHT_BRACKET.literal), tokens[21])
    }

    @Test
    fun testRemainderExpression() {
        val test = "24%32%46==t"
        val arithmeticScanner = ArithmeticScanner()
        val result = arithmeticScanner.scan(test)
        assertEquals(Token(Tokens.IDENTIFIER, "24"), result[0])
        assertEquals(Token(Tokens.REMAINDER, Tokens.REMAINDER.literal), result[1])
        assertEquals(Token(Tokens.IDENTIFIER, "32"), result[2])
        assertEquals(Token(Tokens.REMAINDER, Tokens.REMAINDER.literal), result[3])
        assertEquals(Token(Tokens.IDENTIFIER, "46"), result[4])
        assertEquals(Token(Tokens.EQUAL, Tokens.EQUAL.literal), result[5])
        assertEquals(Token(Tokens.IDENTIFIER, "t"), result[6])
    }

    @Test
    fun testPlusExpression() {
        val test = "24  +  65 == t"
        val arithmeticScanner = ArithmeticScanner()
        val result = arithmeticScanner.scan(test)
        assertEquals(Token(Tokens.IDENTIFIER, "24"), result[0])
        assertEquals(Token(Tokens.PLUS, Tokens.PLUS.literal), result[1])
        assertEquals(Token(Tokens.IDENTIFIER, "65"), result[2])
        assertEquals(Token(Tokens.EQUAL, Tokens.EQUAL.literal), result[3])
        assertEquals(Token(Tokens.IDENTIFIER, "t"), result[4])
    }

    @Test
    fun testMinusExpression() {
        val test = "(34-12)<<22==6"
        val arithmeticScanner = ArithmeticScanner()
        val result = arithmeticScanner.scan(test)
        assertEquals(Token(Tokens.LEFT_BRACKET, Tokens.LEFT_BRACKET.literal), result[0])
        assertEquals(Token(Tokens.IDENTIFIER, "34"), result[1])
        assertEquals(Token(Tokens.MINUS, Tokens.MINUS.literal), result[2])
        assertEquals(Token(Tokens.IDENTIFIER, "12"), result[3])
        assertEquals(Token(Tokens.RIGHT_BRACKET, Tokens.RIGHT_BRACKET.literal), result[4])
        assertEquals(Token(Tokens.SHIFT_LEFT, Tokens.SHIFT_LEFT.literal), result[5])
        assertEquals(Token(Tokens.IDENTIFIER, "22"), result[6])
        assertEquals(Token(Tokens.EQUAL, Tokens.EQUAL.literal), result[7])
        assertEquals(Token(Tokens.IDENTIFIER, "6"), result[8])
    }

    @Test
    fun testScanWithDoubleAnd() {
        val test = " k == 2 && p == 7"
        val arithmeticScanner = ArithmeticScanner()
        val result = arithmeticScanner.scan(test)
        assertEquals(Token(Tokens.IDENTIFIER, "k"), result[0])
        assertEquals(Token(Tokens.EQUAL, Tokens.EQUAL.literal), result[1])
        assertEquals(Token(Tokens.IDENTIFIER, "2"), result[2])
        assertEquals(Token(Tokens.AND_DOUBLE, Tokens.AND_DOUBLE.literal), result[3])
        assertEquals(Token(Tokens.IDENTIFIER, "p"), result[4])
        assertEquals(Token(Tokens.EQUAL, Tokens.EQUAL.literal), result[5])
        assertEquals(Token(Tokens.IDENTIFIER, "7"), result[6])
    }

    @Test
    fun testScanWithOr() {
        val test = "l ==7 || p !=4 && k == 3"
        val arithmeticScanner = ArithmeticScanner()
        val result = arithmeticScanner.scan(test)
        assertEquals(Token(Tokens.IDENTIFIER, "l"), result[0])
        assertEquals(Token(Tokens.EQUAL, Tokens.EQUAL.literal), result[1])
        assertEquals(Token(Tokens.IDENTIFIER, "7"), result[2])
        assertEquals(Token(Tokens.SLASH_DOUBLE, Tokens.SLASH_DOUBLE.literal), result[3])
        assertEquals(Token(Tokens.IDENTIFIER, "p"), result[4])
        assertEquals(Token(Tokens.NOT_EQUAL_C, Tokens.NOT_EQUAL_C.literal), result[5])
        assertEquals(Token(Tokens.IDENTIFIER, "4"), result[6])
        assertEquals(Token(Tokens.AND_DOUBLE, Tokens.AND_DOUBLE.literal), result[7])
        assertEquals(Token(Tokens.IDENTIFIER, "k"), result[8])
        assertEquals(Token(Tokens.EQUAL, Tokens.EQUAL.literal), result[9])
        assertEquals(Token(Tokens.IDENTIFIER, "3"), result[10])
    }

    @Test
    fun testScanDivider() {
        val test = "l // p"
        val arithmeticScanner = ArithmeticScanner()
        val tokens = arithmeticScanner.scan(test)
        assertEquals(Token(Tokens.IDENTIFIER, "l"), tokens[0])
        assertEquals(Token(Tokens.FLOOR_DIVISION, Tokens.FLOOR_DIVISION.literal), tokens[1])
        assertEquals(Token(Tokens.IDENTIFIER, "p"), tokens[2])
    }
}