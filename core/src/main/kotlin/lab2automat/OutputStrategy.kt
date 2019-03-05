package lab2automat

interface OutputStrategy {
    fun print(s: String)

    fun println(s: String="")

    fun clear(s: String)
}