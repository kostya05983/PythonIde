package scanner.ariphmeticScanAutomat

import org.junit.jupiter.api.Assertions.*
import scanner.Token
import scanner.Tokens

internal class ArithmeticScannerTest {

    val test = """
        a ==5 and test==7<<2&238 and teta!=~(25>>34<<32)
    """.trimIndent()


    @org.junit.jupiter.api.Test
    fun scan() {
        val arithmeticScanner = ArithmeticScanner()
        arithmeticScanner.scan(test)
        val tokens = arithmeticScanner.currenState.tokensArray
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
        assertEquals(Token(Tokens.NOT_EQUAL, Tokens.NOT_EQUAL.literal), tokens[13])
        assertEquals(Token(Tokens.INVERSE, Tokens.INVERSE.literal), tokens[14])
        assertEquals(Token(Tokens.LEFT_BRACKET, Tokens.LEFT_BRACKET.literal), tokens[15])
        assertEquals(Token(Tokens.IDENTIFIER, "25"), tokens[16])
        assertEquals(Token(Tokens.SHIFT_RIGHT, Tokens.SHIFT_RIGHT.literal), tokens[17])
        assertEquals(Token(Tokens.IDENTIFIER, "34"), tokens[18])
        assertEquals(Token(Tokens.SHIFT_LEFT, Tokens.SHIFT_LEFT.literal), tokens[19])
        assertEquals(Token(Tokens.IDENTIFIER, "32"), tokens[20])
        assertEquals(Token(Tokens.RIGHT_BRACKET, Tokens.RIGHT_BRACKET.literal), tokens[21])
    }
}