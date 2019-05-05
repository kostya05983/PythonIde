package syntax

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import scanner.ScannerAutomate

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
            val scanner = ScannerAutomate()
            val tokens = scanner.scan(line)

            val analyzer = SyntaxAnalyzer()
            val errors = analyzer.analyze(tokens)
            assertEquals(0, errors.size)
        }

        @Test
        fun testAnalyzerOnlyIfStatement() {
            val line = """
            if k == 2 and p == 7:
                k = 6
        """.trimIndent()
            val scanner = ScannerAutomate()
            val tokens = scanner.scan(line)

            val analyzer = SyntaxAnalyzer()
            val errors = analyzer.analyze(tokens)
            assertEquals(0, errors.size)
        }

        @Test
        fun testAnalyzeOnluifStatement() {
            val line = """
                if k == 2:
                    l=4
            """.trimIndent()
            val scanner = ScannerAutomate()
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
            val scanner = ScannerAutomate()
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
            val scanner = ScannerAutomate()
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
            val scanner = ScannerAutomate()
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
            val scanner = ScannerAutomate()
            val tokens = scanner.scan(line)

            val analyzer = SyntaxAnalyzer()
            val errors = analyzer.analyze(tokens)
            assertEquals(0, errors.size)
        }
    }

    @Test
    fun test() {
        val line = "if k==2:\n" +
                "    t=4\n"
        val scanner = ScannerAutomate()
        val tokens = scanner.scan(line)

        val analyzer = SyntaxAnalyzer()
        val errors = analyzer.analyze(tokens)
        assertEquals(0, errors.size)
    }

    @Test
    fun testAnalyzeError() {
        val line = """
            if k==2:
                p=6
            else t==:
               t=8
        """.trimIndent()
        val scanner = ScannerAutomate()
        val tokens = scanner.scan(line)

        val analyzer = SyntaxAnalyzer()
        val errors = analyzer.analyze(tokens)
        assertEquals(2, errors.size)
    }

    @Nested
    inner class AnalyzeErrorsWhenNothingAfter {
        /**
         * Test when after if nothing
         */
        @Test
        fun testAnalyzeErrorsWhenOnlyIf() {
            val line = "if "
            val scanner = ScannerAutomate()
            val tokens = scanner.scan(line)

            val analyzer = SyntaxAnalyzer()
            val errors = analyzer.analyze(tokens)
            assertNotEquals(0, errors.size)
        }

        /**
         * Test when after and nothing
         */
        @Test
        fun testAnalyzeErrorsNothingAfterAnd() {
            val line = "if k==2 and "
            val scanner = ScannerAutomate()
            val tokens = scanner.scan(line)

            val analyzer = SyntaxAnalyzer()
            val errors = analyzer.analyze(tokens)
            assertNotEquals(0, errors.size)
        }

        @Test
        fun testAnalyzeErrorsNothingAfterOr() {
            val line = "if k==2 or "
            val scanner = ScannerAutomate()
            val tokens = scanner.scan(line)

            val analyzer = SyntaxAnalyzer()
            val errors = analyzer.analyze(tokens)
            assertNotEquals(0, errors.size)
        }
    }

    @Test
    fun testIfWithNotEndingIdentifier() {
        val line = "if k:"
        val scanner = ScannerAutomate()
        val tokens = scanner.scan(line)

        val analyzer = SyntaxAnalyzer()
        val errors = analyzer.analyze(tokens)
        assertNotEquals(0, errors.size)
    }

    @Test
    fun testWithDoubleAnd() {
        val line = "if k==2 and and or"
        val scanner = ScannerAutomate()
        val tokens = scanner.scan(line)

        val analyzer = SyntaxAnalyzer()
        val errors = analyzer.analyze(tokens)
        assertNotEquals(0, errors.size)
    }

    @Nested
    inner class TestWithoutColon {

        @Test
        fun testWithoutColon() {
            val line = "if k==2"
            val scanner = ScannerAutomate()
            val tokens = scanner.scan(line)

            val analyzer = SyntaxAnalyzer()
            val errors = analyzer.analyze(tokens)
            assertNotEquals(0, errors.size)
        }

        @Test
        fun testWithoutElifColon() {
            val line = """
                if k==2:
                    t=5
                elif t==8
            """.trimIndent()
            val scanner = ScannerAutomate()
            val tokens = scanner.scan(line)

            val analyzer = SyntaxAnalyzer()
            val errors = analyzer.analyze(tokens)
            assertNotEquals(0, errors.size)
        }

        @Test
        fun testWithoutElseColon() {
            val line = """
                if k==2:
                    t=5
                elif t==8:
                    t=8
                else
            """.trimIndent()
            val scanner = ScannerAutomate()
            val tokens = scanner.scan(line)

            val analyzer = SyntaxAnalyzer()
            val errors = analyzer.analyze(tokens)
            assertNotEquals(0, errors.size)
        }
    }

    @Nested
    inner class TestWithoutEndingStatement {
        @Test
        fun testWithoutEndingElse() {
            val line = """
            if k==2:
                t=5
            elif t==5:
                p=6
            else:
        """.trimIndent()
            val scanner = ScannerAutomate()
            val tokens = scanner.scan(line)

            val analyzer = SyntaxAnalyzer()
            val errors = analyzer.analyze(tokens)
            assertNotEquals(0, errors.size)
        }

        @Test
        fun testWithoutEndingElseWithEnter() {
            val line = """
            if k==2:
                t=5
            elif t==5:
                p=6
            else:

        """.trimIndent()
            val scanner = ScannerAutomate()
            val tokens = scanner.scan(line)

            val analyzer = SyntaxAnalyzer()
            val errors = analyzer.analyze(tokens)
            assertNotEquals(0, errors.size)
        }

        @Test
        fun testWithoutEndingIf() {
            val line = """
                if k==2:
            """.trimIndent()
            val scanner = ScannerAutomate()
            val tokens = scanner.scan(line)

            val analyzer = SyntaxAnalyzer()
            val errors = analyzer.analyze(tokens)
            assertNotEquals(0, errors.size)
        }

        @Test
        fun testWithoutEndingIfWithEnter() {
            val line = """
                if k==2:

            """.trimIndent()
            val scanner = ScannerAutomate()
            val tokens = scanner.scan(line)

            val analyzer = SyntaxAnalyzer()
            val errors = analyzer.analyze(tokens)
            assertNotEquals(0, errors.size)
        }

        @Test
        fun testWithoutEndingElif() {
            val line = """
                if k==2:
                    t=5
                elif p=5:
            """.trimIndent()
            val scanner = ScannerAutomate()
            val tokens = scanner.scan(line)

            val analyzer = SyntaxAnalyzer()
            val errors = analyzer.analyze(tokens)
            assertNotEquals(0, errors.size)
        }

        @Test
        fun testWithoutEndingElifWithEnter() {
            val line = """
                if k==2:
                    t=5
                elif p=5:

            """.trimIndent()
            val scanner = ScannerAutomate()
            val tokens = scanner.scan(line)

            val analyzer = SyntaxAnalyzer()
            val errors = analyzer.analyze(tokens)
            assertNotEquals(0, errors.size)
        }
    }

    @Nested
    inner class NeutralizationTest {

        @Test
        fun testFirstIfErrorNextComplete() {
            val line = """
                if t=:

                if k==2:
                    t=6
            """.trimIndent()
            val scanner = ScannerAutomate()
            val tokens = scanner.scan(line)

            val analyzer = SyntaxAnalyzer()
            val errors = analyzer.analyze(tokens)
            println()
        }
    }
}