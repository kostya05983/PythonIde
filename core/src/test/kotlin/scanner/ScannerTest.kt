package scanner

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import syntax.SyntaxAnalyzer

internal class ScannerTest {

    @Test
    fun testScanOnlyIfStatementS() {
        val line = """
            suite
            suite
        """.trimIndent()
        val scanner = ScannerAutomate()
        val tokens = scanner.scan(line)
        assertEquals(Token(Tokens.SIMPLE_STMT, "suite"), tokens[0])
        assertEquals(Token(Tokens.NEWLINE, Tokens.NEWLINE.literal), tokens[1])
        assertEquals(Token(Tokens.SIMPLE_STMT, "suite"), tokens[2])
    }

    @Test
    fun testScanOnlyIfStatement() {
        val line = """
            if k == 2 and p == 7:
        """.trimIndent()
        val scanner = ScannerAutomate()
        val tokens = scanner.scan(line)
        assertEquals(Token(Tokens.IF, "if "), tokens[0])
        assertEquals(Token(Tokens.IDENTIFIER, "k "), tokens[1])
        assertEquals(Token(Tokens.EQUAL, Tokens.EQUAL.literal), tokens[2])
        assertEquals(Token(Tokens.IDENTIFIER, " 2 "), tokens[3])
        assertEquals(Token(Tokens.AND, "and "), tokens[4])
        assertEquals(Token(Tokens.IDENTIFIER, "p "), tokens[5])
        assertEquals(Token(Tokens.EQUAL, Tokens.EQUAL.literal), tokens[6])
        assertEquals(Token(Tokens.IDENTIFIER, " 7"), tokens[7])
        assertEquals(Token(Tokens.COLON, Tokens.COLON.literal), tokens[8])
    }

    @Test
    fun testScanStatementsWithIf() {
        val line = """
            simple_stmt
            simple_stmt
            simple_stmt
            if l ==7 or p !=4 and k == 3:
                simple_stmt
        """.trimIndent()
        val scanner = ScannerAutomate()
        val tokens = scanner.scan(line)
        assertEquals(Token(Tokens.SIMPLE_STMT, "simple_stmt"), tokens[0])
        assertEquals(Token(Tokens.NEWLINE, Tokens.NEWLINE.literal), tokens[1])
        assertEquals(Token(Tokens.SIMPLE_STMT, "simple_stmt"), tokens[2])
        assertEquals(Token(Tokens.NEWLINE, Tokens.NEWLINE.literal), tokens[3])
        assertEquals(Token(Tokens.SIMPLE_STMT, "simple_stmt"), tokens[4])
        assertEquals(Token(Tokens.NEWLINE, Tokens.NEWLINE.literal), tokens[5])

        assertEquals(Token(Tokens.IF, "if "), tokens[6])
        assertEquals(Token(Tokens.IDENTIFIER, "l "), tokens[7])
        assertEquals(Token(Tokens.EQUAL, Tokens.EQUAL.literal), tokens[8])
        assertEquals(Token(Tokens.IDENTIFIER, "7 "), tokens[9])
        assertEquals(Token(Tokens.OR, "or "), tokens[10])
        assertEquals(Token(Tokens.IDENTIFIER, "p "), tokens[11])
        assertEquals(Token(Tokens.NOT_EQUAL_C, Tokens.NOT_EQUAL_C.literal), tokens[12])
        assertEquals(Token(Tokens.IDENTIFIER, "4 "), tokens[13])
        assertEquals(Token(Tokens.AND, "and "), tokens[14])
        assertEquals(Token(Tokens.IDENTIFIER, "k "), tokens[15])
        assertEquals(Token(Tokens.EQUAL, Tokens.EQUAL.literal), tokens[16])
        assertEquals(Token(Tokens.IDENTIFIER, " 3"), tokens[17])
        assertEquals(Token(Tokens.COLON, Tokens.COLON.literal), tokens[18])

        assertEquals(Token(Tokens.NEWLINE, Tokens.NEWLINE.literal), tokens[19])
        assertEquals(Token(Tokens.INDENT, Tokens.INDENT.literal), tokens[20])
        assertEquals(Token(Tokens.SIMPLE_STMT, "simple_stmt"), tokens[21])
    }

    @Test
    fun testScanStatementsWithElseIf() {
        val line = """
           simple_stmt
           if k == 4 <<2:
               simple_stmt
           elif k==2:
               simple_stmt
        """.trimIndent()
        val scanner = ScannerAutomate()
        val tokens = scanner.scan(line)

        assertEquals(Token(Tokens.SIMPLE_STMT, "simple_stmt"), tokens[0])
        assertEquals(Token(Tokens.NEWLINE, Tokens.NEWLINE.literal), tokens[1])

        assertEquals(Token(Tokens.IF, "if "), tokens[2])
        assertEquals(Token(Tokens.IDENTIFIER, "k "), tokens[3])
        assertEquals(Token(Tokens.EQUAL, Tokens.EQUAL.literal), tokens[4])
        assertEquals(Token(Tokens.IDENTIFIER, " 4 "), tokens[5])
        assertEquals(Token(Tokens.SHIFT_LEFT, Tokens.SHIFT_LEFT.literal), tokens[6])
        assertEquals(Token(Tokens.IDENTIFIER, "2"), tokens[7])
        assertEquals(Token(Tokens.COLON, Tokens.COLON.literal), tokens[8])
        assertEquals(Token(Tokens.NEWLINE, Tokens.NEWLINE.literal), tokens[9])

        assertEquals(Token(Tokens.INDENT, Tokens.INDENT.literal), tokens[10])
        assertEquals(Token(Tokens.SIMPLE_STMT, "simple_stmt"), tokens[11])
        assertEquals(Token(Tokens.NEWLINE, Tokens.NEWLINE.literal), tokens[12])

        assertEquals(Token(Tokens.ELIF, Tokens.ELIF.literal), tokens[13])
        assertEquals(Token(Tokens.IDENTIFIER, " k"), tokens[14])
        assertEquals(Token(Tokens.EQUAL, Tokens.EQUAL.literal), tokens[15])
        assertEquals(Token(Tokens.IDENTIFIER, "2"), tokens[16])
        assertEquals(Token(Tokens.COLON, Tokens.COLON.literal), tokens[17])
        assertEquals(Token(Tokens.NEWLINE, Tokens.NEWLINE.literal), tokens[18])

        assertEquals(Token(Tokens.INDENT, Tokens.INDENT.literal), tokens[19])
        assertEquals(Token(Tokens.SIMPLE_STMT, "simple_stmt"), tokens[20])
    }

    @Test
    fun testScanStatementWithElseIfElse() {
        val line = """
            simple_stmt
            if k == 4<<4:
                simple_stmt
            elif k==6:
                simple_stmt
            else:
                simple_stmt
        """.trimIndent()
        val scanner = ScannerAutomate()
        val tokens = scanner.scan(line)

        assertEquals(Token(Tokens.SIMPLE_STMT, "simple_stmt"), tokens[0])
        assertEquals(Token(Tokens.NEWLINE, Tokens.NEWLINE.literal), tokens[1])

        assertEquals(Token(Tokens.IF, "if "), tokens[2])
        assertEquals(Token(Tokens.IDENTIFIER, "k "), tokens[3])
        assertEquals(Token(Tokens.EQUAL, Tokens.EQUAL.literal), tokens[4])
        assertEquals(Token(Tokens.IDENTIFIER, " 4"), tokens[5])
        assertEquals(Token(Tokens.SHIFT_LEFT, Tokens.SHIFT_LEFT.literal), tokens[6])
        assertEquals(Token(Tokens.IDENTIFIER, "4"), tokens[7])
        assertEquals(Token(Tokens.COLON, Tokens.COLON.literal), tokens[8])
        assertEquals(Token(Tokens.NEWLINE, Tokens.NEWLINE.literal), tokens[9])

        assertEquals(Token(Tokens.INDENT, Tokens.INDENT.literal), tokens[10])
        assertEquals(Token(Tokens.SIMPLE_STMT, "simple_stmt"), tokens[11])
        assertEquals(Token(Tokens.NEWLINE, Tokens.NEWLINE.literal), tokens[12])

        assertEquals(Token(Tokens.ELIF, Tokens.ELIF.literal), tokens[13])
        assertEquals(Token(Tokens.IDENTIFIER, " k"), tokens[14])
        assertEquals(Token(Tokens.EQUAL, Tokens.EQUAL.literal), tokens[15])
        assertEquals(Token(Tokens.IDENTIFIER, "6"), tokens[16])
        assertEquals(Token(Tokens.COLON, Tokens.COLON.literal), tokens[17])
        assertEquals(Token(Tokens.NEWLINE, Tokens.NEWLINE.literal), tokens[18])

        assertEquals(Token(Tokens.INDENT, Tokens.INDENT.literal), tokens[19])
        assertEquals(Token(Tokens.SIMPLE_STMT, "simple_stmt"), tokens[20])
        assertEquals(Token(Tokens.NEWLINE, Tokens.NEWLINE.literal), tokens[21])

        assertEquals(Token(Tokens.ELSE, Tokens.ELSE.literal), tokens[22])
        assertEquals(Token(Tokens.COLON, Tokens.COLON.literal), tokens[23])
        assertEquals(Token(Tokens.NEWLINE, Tokens.NEWLINE.literal), tokens[24])
        assertEquals(Token(Tokens.INDENT, Tokens.INDENT.literal), tokens[25])
        assertEquals(Token(Tokens.SIMPLE_STMT, "simple_stmt"), tokens[26])
    }

    @Nested
    inner class TestOffset {

        @Test
        fun testWithErrorsInElse() {
            val line = """
            if t ==4:
                simple_stmt
            elif t=:
        """.trimIndent()

            val scanner = ScannerAutomate()
            val tokens = scanner.scan(line)

            assertEquals(Token(Tokens.IF, "if "), tokens[0])
            assertEquals(0, tokens[0].paragraph)
            assertEquals(0, tokens[0].startPosition)
            assertEquals(2, tokens[0].endPosition)

            assertEquals(Token(Tokens.IDENTIFIER, "t "), tokens[1])
            assertEquals(0, tokens[1].paragraph)
            assertEquals(3, tokens[1].startPosition)
            assertEquals(4, tokens[1].endPosition)

            assertEquals(Token(Tokens.EQUAL, "=="), tokens[2])
            assertEquals(0, tokens[2].paragraph)
            assertEquals(5, tokens[2].startPosition)
            assertEquals(6, tokens[2].endPosition)

            assertEquals(Token(Tokens.IDENTIFIER, "4"), tokens[3])
            assertEquals(0, tokens[3].paragraph)
            assertEquals(7, tokens[3].startPosition)
            assertEquals(7, tokens[3].endPosition)

            assertEquals(Token(Tokens.COLON, ":"), tokens[4])
            assertEquals(0, tokens[4].paragraph)
            assertEquals(8, tokens[4].startPosition)
            assertEquals(8, tokens[4].endPosition)

            assertEquals(Token(Tokens.NEWLINE, "\n"), tokens[5])
            assertEquals(0, tokens[5].paragraph)
            assertEquals(9, tokens[5].startPosition)
            assertEquals(9, tokens[5].endPosition)

            assertEquals(Token(Tokens.INDENT, Tokens.INDENT.literal), tokens[6])
            assertEquals(1, tokens[6].paragraph)
            assertEquals(0, tokens[6].startPosition)
            assertEquals(3, tokens[6].endPosition)

            assertEquals(Token(Tokens.SIMPLE_STMT, "simple_stmt"), tokens[7])
            assertEquals(1, tokens[7].paragraph)
            assertEquals(4, tokens[7].startPosition)
            assertEquals(14, tokens[7].endPosition)

            assertEquals(Token(Tokens.NEWLINE, "\n"), tokens[8])

            assertEquals(Token(Tokens.ELIF, "elif"), tokens[9])
            assertEquals(2, tokens[9].paragraph)
            assertEquals(0, tokens[9].startPosition)
            assertEquals(3, tokens[9].endPosition)

            assertEquals(2, tokens[10].paragraph)
        }

    }

    @Nested
    inner class NeutralizationScanTest {

        @Test
        fun testFirstIfErrorNextComplete() {
            val line = """
                if t=:

                if k==2:
                    suite
            """.trimIndent()
            val scanner = ScannerAutomate()
            val tokens = scanner.scan(line)
            assertEquals(Token(Tokens.SIMPLE_STMT, "suite"), tokens[12])
        }
    }

    @Nested
    inner class ComparsionScanTest {
        @Test
        fun testScanLess() {
            val line = """
                if t<p:
                    suite
            """.trimIndent()
            val scanner = ScannerAutomate()
            val tokens = scanner.scan(line)

            assertEquals(Token(Tokens.IF, "if "), tokens[0])
            assertEquals(Token(Tokens.IDENTIFIER, "t"), tokens[1])
            assertEquals(Token(Tokens.LESS, "<"), tokens[2])
            assertEquals(Token(Tokens.IDENTIFIER, "p"), tokens[3])
            assertEquals(Token(Tokens.COLON, ":"), tokens[4])
            assertEquals(Token(Tokens.NEWLINE, Tokens.NEWLINE.literal), tokens[5])
            assertEquals(Token(Tokens.INDENT, Tokens.INDENT.literal), tokens[6])
            assertEquals(Token(Tokens.SIMPLE_STMT, "suite"), tokens[7])
        }

        @Test
        fun testScanLessEqual() {
            val line = """
                if t<=p:
                    suite
            """.trimIndent()
            val scanner = ScannerAutomate()
            val tokens = scanner.scan(line)

            assertEquals(Token(Tokens.IF, "if "), tokens[0])
            assertEquals(Token(Tokens.IDENTIFIER, "t"), tokens[1])
            assertEquals(Token(Tokens.LESS_EQUAL, "<="), tokens[2])
            assertEquals(Token(Tokens.IDENTIFIER, "p"), tokens[3])
            assertEquals(Token(Tokens.COLON, ":"), tokens[4])
            assertEquals(Token(Tokens.NEWLINE, Tokens.NEWLINE.literal), tokens[5])
            assertEquals(Token(Tokens.INDENT, Tokens.INDENT.literal), tokens[6])
            assertEquals(Token(Tokens.SIMPLE_STMT, "suite"), tokens[7])
        }

        @Test
        fun testScanMore() {
            val line = """
                if t>p:
                    suite
            """.trimIndent()
            val scanner = ScannerAutomate()
            val tokens = scanner.scan(line)

            assertEquals(Token(Tokens.IF, "if "), tokens[0])
            assertEquals(Token(Tokens.IDENTIFIER, "t"), tokens[1])
            assertEquals(Token(Tokens.MORE, ">"), tokens[2])
            assertEquals(Token(Tokens.IDENTIFIER, "p"), tokens[3])
            assertEquals(Token(Tokens.COLON, ":"), tokens[4])
            assertEquals(Token(Tokens.NEWLINE, Tokens.NEWLINE.literal), tokens[5])
            assertEquals(Token(Tokens.INDENT, Tokens.INDENT.literal), tokens[6])
            assertEquals(Token(Tokens.SIMPLE_STMT, "suite"), tokens[7])
        }

        @Test
        fun testScanMoreEqual() {
            val line = """
                if t>=p:
                    suite
            """.trimIndent()
            val scanner = ScannerAutomate()
            val tokens = scanner.scan(line)

            assertEquals(Token(Tokens.IF, "if "), tokens[0])
            assertEquals(Token(Tokens.IDENTIFIER, "t"), tokens[1])
            assertEquals(Token(Tokens.MORE_EQUAL, ">="), tokens[2])
            assertEquals(Token(Tokens.IDENTIFIER, "p"), tokens[3])
            assertEquals(Token(Tokens.COLON, ":"), tokens[4])
            assertEquals(Token(Tokens.NEWLINE, Tokens.NEWLINE.literal), tokens[5])
            assertEquals(Token(Tokens.INDENT, Tokens.INDENT.literal), tokens[6])
            assertEquals(Token(Tokens.SIMPLE_STMT, "suite"), tokens[7])
        }


    }


}