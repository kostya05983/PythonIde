package syntax

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import scanner.Scanner

internal class SyntaxAnalyzerTest {

    @Nested
    inner class WithoutErrors {
        @Test
        fun testAnalyzeOnlyStatement() {
            val line = """
            k = 2
            t = Test(2)
            Test(4,6)
        """.trimIndent()
            val scanner = Scanner()
            val tokens = scanner.scan(line)

            val analyzer = SyntaxAnalyzer()
            val errors = analyzer.analyze(tokens)
            assertEquals(0, errors.size)
        }

        @Test
        fun testAnalyzerOnlyIfStatement() {
            val line = """
            if k == 2 && p == 7:
                k = 6
        """.trimIndent()
            val scanner = Scanner()
            val tokens = scanner.scan(line)

            val analyzer = SyntaxAnalyzer()
            val errors = analyzer.analyze(tokens)
            assertEquals(0, errors.size)
        }

        @Test
        fun testAnalyzeIfElIfStatement() {
            val line = """
           k = 2
           if k == 4 <<2:
               p=2
           elif k==2:
               t = 4
        """.trimIndent()
            val scanner = Scanner()
            val tokens = scanner.scan(line)

            val analyzer = SyntaxAnalyzer()
            val errors = analyzer.analyze(tokens)
            assertEquals(0, errors.size)
        }

        @Test
        fun testAnalyzeFullExpression() {
            val line = """
            k = 2
            if k == 4 <<4:
                p = 2
            elif k==7:
                t = 7
            else:
                p =8
        """.trimIndent()
            val scanner = Scanner()
            val tokens = scanner.scan(line)

            val analyzer = SyntaxAnalyzer()
            val errors = analyzer.analyze(tokens)
            assertEquals(0, errors.size)

        }

        @Test
        fun testAnalyzeIf() {
            val line = """
                if k ==2:
                    t ==5
            """.trimIndent()
            val scanner = Scanner()
            val tokens = scanner.scan(line)

            val analyzer = SyntaxAnalyzer()
            val errors = analyzer.analyze(tokens)
            assertEquals(0, errors.size)
        }

        @Test
        fun testAnalyzeIfWithEnter() {
            val line = """
                if k==4:
                    t==5

            """.trimIndent()
            val scanner = Scanner()
            val tokens = scanner.scan(line)

            val analyzer = SyntaxAnalyzer()
            val errors = analyzer.analyze(tokens)
            assertEquals(0, errors.size)
        }
    }

    @Test
    fun testAnalyzeError() {
        val line = """
            if k==2:
                p=6
            else t==:
               t=8
        """.trimIndent()
        val scanner = Scanner()
        val tokens = scanner.scan(line)

        val analyzer = SyntaxAnalyzer()
        val errors = analyzer.analyze(tokens)
        assertEquals(1, errors.size)
    }
}