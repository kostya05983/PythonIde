package scanner

class Token(val token: Tokens, val value: String) {
    override fun equals(other: Any?): Boolean {
        val otherToken = other as Token
        return token == otherToken.token && value == otherToken.value
    }
}