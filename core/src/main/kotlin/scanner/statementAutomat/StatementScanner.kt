package scanner.statementAutomat

import scanner.Token
import java.util.*

class StatementScanner {
    var currentState: State = MainState(LinkedList(), Stack(), this)

    /**
     * We can throw error if our end state not end, that's it!
     */
    fun scan(line: String): List<Token> {
        for (char in line)
            currentState.scan(char)

        if(currentState !is SimpleStatementState)
            TODO("ERROR not close (")
        return currentState.tokenArray
    }

    fun changeState(newState: State) {
        currentState = newState
    }
}