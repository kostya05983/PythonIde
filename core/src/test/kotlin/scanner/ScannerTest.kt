package scanner

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ScannerTest {

    @Test
    fun testScanOnlyStatements() {
        val line = """
            k = 2
            t = Test(2)
            Test(4,6)
        """.trimIndent()
        val scanner = Scanner()
        val tokens = scanner.scan(line)
        assertEquals(Token(Tokens.SIMPLE_STMT, "k = 2"), tokens[0])
        assertEquals(Token(Tokens.SIMPLE_STMT, "t = Test(2)"), tokens[1])
        assertEquals(Token(Tokens.SIMPLE_STMT, "Test(4,6)"), tokens[2])
    }

    @Test
    fun testScanOnlyIfStatement() {
        val line = """
            if k == 2 && p == 7:
        """.trimIndent()
        val scanner = Scanner()
        val tokens = scanner.scan(line)
        assertEquals(Token(Tokens.IF, "if"), tokens[0])
        assertEquals(Token(Tokens.IDENTIFIER, "k"), tokens[1])
        assertEquals(Token(Tokens.EQUAL, Tokens.EQUAL.literal), tokens[2])
        assertEquals(Token(Tokens.IDENTIFIER, "2"), tokens[3])
        assertEquals(Token(Tokens.AND_DOUBLE, Tokens.AND_DOUBLE.literal), tokens[4])
        assertEquals(Token(Tokens.IDENTIFIER, "p"), tokens[5])
        assertEquals(Token(Tokens.EQUAL, Tokens.EQUAL.literal), tokens[6])
        assertEquals(Token(Tokens.IDENTIFIER, "7"), tokens[7])
    }

    @Test
    fun testScanStatementsWithIf() {
        val line = """
            k = 2
            p = 4
            l = 7
            if l ==7 || p !=4 && k == 3:
                k=2
        """.trimIndent()
        val scanner = Scanner()
        val tokens = scanner.scan(line)
        assertEquals(Token(Tokens.SIMPLE_STMT, "k = 2"), tokens[0])
        assertEquals(Token(Tokens.SIMPLE_STMT, "p = 4"), tokens[1])
        assertEquals(Token(Tokens.SIMPLE_STMT, "l = 7"), tokens[2])
        assertEquals(Token(Tokens.IF, Tokens.IF.literal), tokens[3])
        assertEquals(Token(Tokens.IDENTIFIER, "l"), tokens[4])
        assertEquals(Token(Tokens.EQUAL, Tokens.EQUAL.literal), tokens[5])
        assertEquals(Token(Tokens.IDENTIFIER, "7"), tokens[6])
        assertEquals(Token(Tokens.SLASH_DOUBLE, Tokens.SLASH_DOUBLE.literal), tokens[7])
        assertEquals(Token(Tokens.IDENTIFIER, "p"), tokens[8])
        assertEquals(Token(Tokens.NOT_EQUAL_C, Tokens.NOT_EQUAL_C.literal), tokens[9])
        assertEquals(Token(Tokens.IDENTIFIER, "4"), tokens[10])
        assertEquals(Token(Tokens.AND_DOUBLE, Tokens.AND_DOUBLE.literal), tokens[11])
        assertEquals(Token(Tokens.IDENTIFIER, "k"), tokens[12])
        assertEquals(Token(Tokens.EQUAL, Tokens.EQUAL.literal), tokens[13])
        assertEquals(Token(Tokens.IDENTIFIER, "3"), tokens[14])
        assertEquals(Token(Tokens.INDENT, Tokens.INDENT.literal), tokens[15])
        assertEquals(Token(Tokens.SIMPLE_STMT, "k=2"), tokens[16])
    }
}