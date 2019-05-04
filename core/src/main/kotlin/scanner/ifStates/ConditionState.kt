package scanner.ifStates

import scanner.Token
import scanner.State
import java.util.*
import scanner.Alphabet
import scanner.ScannerAutomate
import scanner.Tokens

class ConditionState(override val scanner: ScannerAutomate,
                     override val tokensArray: LinkedList<Token>,
                     override val memory: Stack<Char>,
                     override var offset: Int,
                     override var page: Int) : State {

    override fun parse(char: Char) {
        when {
            memory.empty() && char == Alphabet.I.ch -> {
                memory.push(char)
                scanner.changeState(IState(scanner, tokensArray, memory, offset, page))
            }
            memory.empty() && Alphabet.E.ch == char -> {
                memory.push(char)
                scanner.changeState(ElState(scanner, tokensArray, memory, offset, page))
            }
            Alphabet.COLON.ch == char -> {
                addUnrecognized()
                memory.push(char)
                tokensArray.add(Token(Tokens.COLON, memory, offset, page))
                memory.clear()
            }
            !memory.empty() && memory.peek() == Alphabet.COLON.ch && Alphabet.NEWLINE.ch == char -> {
                memory.clear()
                memory.push(char)
                tokensArray.add(Token(Tokens.NEWLINE, memory, offset, page))
                memory.clear()
                offset = -1
                page++
            }
            Alphabet.NEWLINE.ch == char -> {
                tokensArray.addAll(scanner.joinToSimpleStatementWithIndent(memory, offset, page))
                memory.clear()
                memory.push(char)
                tokensArray.add(Token(Tokens.NEWLINE, memory, offset, page))
                memory.clear()
                page++
                offset = -1
            }
            else -> {
                memory.push(char)
            }
        }
    }

    private fun addUnrecognized() {
        if (memory.isNotEmpty()) {
            tokensArray.add(Token(Tokens.UNRECOGNIZED, memory, offset, page))
            memory.clear()
        }
    }
}