package scanner

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ScannerTest {

    @Test
    fun scanOnlyStatements() {
        val line = """
            k = 2
            t = Test(2)
            Test(4,6)
        """.trimIndent()

    }
}