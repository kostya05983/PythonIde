package scanner.ariphmeticScanAutomat

import org.junit.jupiter.api.Assertions.*

internal class ArithmeticScannerTest {

    val test = """
        a ==5 and test==7<<2&238 and teta!=~(25>>34<<32)
    """.trimIndent()


    @org.junit.jupiter.api.Test
    fun scan() {
        val arithmeticScanner = ArithmeticScanner()
        arithmeticScanner.scan(test)
        println(arithmeticScanner.currenState.tokensArray)
    }
}