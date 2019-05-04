package scanner

import java.util.*

class Token(val token: Tokens, val value: String) {
    var paragraph: Int? = null
    var startPosition: Int? = null
    var endPosition: Int? = null

    constructor(token: Tokens, memory: Stack<Char>, offset: Int, paragraph: Int) :
            this(token, memory.joinToString("")) {
        this.paragraph = paragraph
        this.startPosition = offset+1 - memory.size
        this.endPosition = offset
    }

    override fun equals(other: Any?): Boolean {
        val otherToken = other as Token
        return token == otherToken.token && value == otherToken.value
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString(): String {
        return "token: $token, value: $value"
    }
}