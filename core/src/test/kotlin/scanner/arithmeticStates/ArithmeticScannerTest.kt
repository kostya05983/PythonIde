package scanner.arithmeticStates

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import scanner.ScannerAutomate
import scanner.Token
import scanner.Tokens

internal class ArithmeticScannerTest {


    @Test
    fun scan() {
        val test = """
        if a ==5 and test==7<<2&238 and teta!=~(25>>34<<32):
    """.trimIndent()
        val scannerAutomate = ScannerAutomate()
        val tokens = scannerAutomate.scan(test)
        assertEquals(Token(Tokens.IF, "if "), tokens[0])
        assertEquals(Token(Tokens.IDENTIFIER, "a "), tokens[1])
        assertEquals(Token(Tokens.EQUAL, Tokens.EQUAL.literal), tokens[2])
        assertEquals(Token(Tokens.IDENTIFIER, "5 "), tokens[3])
        assertEquals(Token(Tokens.AND, "and "), tokens[4])
        assertEquals(Token(Tokens.IDENTIFIER, "test"), tokens[5])
        assertEquals(Token(Tokens.EQUAL, Tokens.EQUAL.literal), tokens[6])
        assertEquals(Token(Tokens.IDENTIFIER, "7"), tokens[7])
        assertEquals(Token(Tokens.SHIFT_LEFT, Tokens.SHIFT_LEFT.literal), tokens[8])
        assertEquals(Token(Tokens.IDENTIFIER, "2"), tokens[9])
        assertEquals(Token(Tokens.BINARY_AND, Tokens.BINARY_AND.literal), tokens[10])
        assertEquals(Token(Tokens.IDENTIFIER, "238 "), tokens[11])
        assertEquals(Token(Tokens.AND, "and "), tokens[12])
        assertEquals(Token(Tokens.IDENTIFIER, "teta"), tokens[13])
        assertEquals(Token(Tokens.NOT_EQUAL_C, Tokens.NOT_EQUAL_C.literal), tokens[14])
        assertEquals(Token(Tokens.INVERSE, Tokens.INVERSE.literal), tokens[15])
        assertEquals(Token(Tokens.LEFT_BRACKET, Tokens.LEFT_BRACKET.literal), tokens[16])
        assertEquals(Token(Tokens.IDENTIFIER, "25"), tokens[17])
        assertEquals(Token(Tokens.SHIFT_RIGHT, Tokens.SHIFT_RIGHT.literal), tokens[18])
        assertEquals(Token(Tokens.IDENTIFIER, "34"), tokens[19])
        assertEquals(Token(Tokens.SHIFT_LEFT, Tokens.SHIFT_LEFT.literal), tokens[20])
        assertEquals(Token(Tokens.IDENTIFIER, "32"), tokens[21])
        assertEquals(Token(Tokens.RIGHT_BRACKET, Tokens.RIGHT_BRACKET.literal), tokens[22])
    }

    @Test
    fun testRemainderExpression() {
        val test = "if 24%32%46==t"
        val scannerAutomate = ScannerAutomate()
        val result = scannerAutomate.scan(test)
        assertEquals(Token(Tokens.IF, "if "), result[0])
        assertEquals(Token(Tokens.IDENTIFIER, "24"), result[1])
        assertEquals(Token(Tokens.REMAINDER, Tokens.REMAINDER.literal), result[2])
        assertEquals(Token(Tokens.IDENTIFIER, "32"), result[3])
        assertEquals(Token(Tokens.REMAINDER, Tokens.REMAINDER.literal), result[4])
        assertEquals(Token(Tokens.IDENTIFIER, "46"), result[5])
        assertEquals(Token(Tokens.EQUAL, Tokens.EQUAL.literal), result[6])
        assertEquals(Token(Tokens.IDENTIFIER, "t"), result[7])
    }

    @Test
    fun testPlusExpression() {
        val test = "if 24  +  65 == t"
        val scannerAutomate = ScannerAutomate()
        val result = scannerAutomate.scan(test)
        assertEquals(Token(Tokens.IF, "if "), result[0])
        assertEquals(Token(Tokens.IDENTIFIER, "24 "), result[1])
        assertEquals(Token(Tokens.PLUS, "+"), result[2])
        assertEquals(Token(Tokens.IDENTIFIER, "  65 "), result[3])
        assertEquals(Token(Tokens.EQUAL, "=="), result[4])
        assertEquals(Token(Tokens.IDENTIFIER, " t"), result[5])
    }

    @Test
    fun testMinusExpression() {
        val test = "if (34-12)<<22==6"
        val scannerAutomate = ScannerAutomate()
        val result = scannerAutomate.scan(test)

        assertEquals(Token(Tokens.IF, "if "), result[0])
        assertEquals(Token(Tokens.LEFT_BRACKET, Tokens.LEFT_BRACKET.literal), result[1])
        assertEquals(Token(Tokens.IDENTIFIER, "34"), result[2])
        assertEquals(Token(Tokens.MINUS, Tokens.MINUS.literal), result[3])
        assertEquals(Token(Tokens.IDENTIFIER, "12"), result[4])
        assertEquals(Token(Tokens.RIGHT_BRACKET, Tokens.RIGHT_BRACKET.literal), result[5])
        assertEquals(Token(Tokens.SHIFT_LEFT, Tokens.SHIFT_LEFT.literal), result[6])
        assertEquals(Token(Tokens.IDENTIFIER, "22"), result[7])
        assertEquals(Token(Tokens.EQUAL, Tokens.EQUAL.literal), result[8])
        assertEquals(Token(Tokens.IDENTIFIER, "6"), result[9])
    }

    @Test
    fun testScanWithDoubleAnd() {
        val test = "if k == 2 and p == 7"
        val scannerAutomate = ScannerAutomate()
        val result = scannerAutomate.scan(test)

        assertEquals(Token(Tokens.IF, "if "), result[0])
        assertEquals(Token(Tokens.IDENTIFIER, "k "), result[1])
        assertEquals(Token(Tokens.EQUAL, "=="), result[2])
        assertEquals(Token(Tokens.IDENTIFIER, " 2 "), result[3])
        assertEquals(Token(Tokens.AND, "and "), result[4])
        assertEquals(Token(Tokens.IDENTIFIER, "p "), result[5])
        assertEquals(Token(Tokens.EQUAL, Tokens.EQUAL.literal), result[6])
        assertEquals(Token(Tokens.IDENTIFIER, " 7"), result[7])
    }

    @Test
    fun testScanWithOr() {
        val test = "if l ==7 or p !=4 and k == 3"
        val scannerAutomate = ScannerAutomate()
        val result = scannerAutomate.scan(test)

        assertEquals(Token(Tokens.IF, "if "), result[0])
        assertEquals(Token(Tokens.IDENTIFIER, "l "), result[1])
        assertEquals(Token(Tokens.EQUAL, Tokens.EQUAL.literal), result[2])
        assertEquals(Token(Tokens.IDENTIFIER, "7 "), result[3])
        assertEquals(Token(Tokens.OR, "or "), result[4])
        assertEquals(Token(Tokens.IDENTIFIER, "p "), result[5])
        assertEquals(Token(Tokens.NOT_EQUAL_C, Tokens.NOT_EQUAL_C.literal), result[6])
        assertEquals(Token(Tokens.IDENTIFIER, "4 "), result[7])
        assertEquals(Token(Tokens.AND, "and "), result[8])
        assertEquals(Token(Tokens.IDENTIFIER, "k "), result[9])
        assertEquals(Token(Tokens.EQUAL, Tokens.EQUAL.literal), result[10])
        assertEquals(Token(Tokens.IDENTIFIER, " 3"), result[11])
    }

    @Test
    fun testScanDivider() {
        val test = "if l // p"
        val scannerAutomate = ScannerAutomate()
        val tokens = scannerAutomate.scan(test)
        assertEquals(Token(Tokens.IF, "if "), tokens[0])
        assertEquals(Token(Tokens.IDENTIFIER, "l "), tokens[1])
        assertEquals(Token(Tokens.FLOOR_DIVISION, Tokens.FLOOR_DIVISION.literal), tokens[2])
        assertEquals(Token(Tokens.IDENTIFIER, " p"), tokens[3])
    }
}