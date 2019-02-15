import kotlin.random.Random

class Generator {

    companion object {
        private const val DEFAULT = 6
        /**
         * default indexes for ascii english letters
         */
        private const val START_INDEX = 65
        private const val END_INDEX = 90
    }

    fun generateStr(amount: Int): String {
        val random = Random.nextInt().toChar()
        val sb = StringBuilder()
        for (i in 0 until amount) {
            val randomChar = Random.nextInt(65, 90).toChar()
            sb.append(randomChar)
        }
        return sb.toString()
    }

    fun generateStr(): String {
        return generateStr(DEFAULT)
    }
}