package scanner.ariphmeticScanAutomat

import scanner.Alphabet
import scanner.Token
import scanner.Tokens
import java.util.*

class MainState(override val scanner: ArithmeticScanner,
                override val tokensArray: LinkedList<Token>,
                override val memory: Stack<Char>) : State {

    //тип пользовтаеля, идентификатор пользователя
    override fun parse(char: Char, currentLine: Int, offset: Int) {
        when (char) {
            Alphabet.PLUS.ch -> {
                addIfNeed(offset, currentLine)
                memory.push(char)
                tokensArray.add(Token(Tokens.PLUS, memory, offset, currentLine))
                memory.clear()
            }
            Alphabet.MINUS.ch -> {
                addIfNeed(offset, currentLine)
                memory.push(char)
                tokensArray.add(Token(Tokens.MINUS, memory, offset, currentLine))
                memory.clear()
            }
            Alphabet.LEFT_SHIFT.ch -> {
                addIfNeed(offset, currentLine)
                memory.push(char)

                scanner.changeState(LeftShiftState(scanner, tokensArray, memory))
            }
            Alphabet.RIGHT_SHIFT.ch -> {
                addIfNeed(offset, currentLine)
                memory.push(char)
                scanner.changeState(RightShiftState(scanner, tokensArray, memory))
            }
            Alphabet.EQUAL.ch -> {
                addIfNeed(offset, currentLine)
                memory.push(char)
                scanner.changeState(EqualState(scanner, tokensArray, memory))
            }
            Alphabet.NOT_EQUAL.ch -> {
                addIfNeed(offset, currentLine)
                memory.push(char)
                scanner.changeState(NotEqualState(scanner, tokensArray, memory))
            }
            Alphabet.REMAINDER.ch -> {
                addIfNeed(offset, currentLine)
                memory.push(char)
                tokensArray.add(Token(Tokens.REMAINDER, memory, offset, currentLine))
                memory.clear()
            }
            Alphabet.DIVIDER.ch -> {
                addIfNeed(offset, currentLine)
                memory.push(char)
                scanner.changeState(DividerState(scanner, tokensArray, memory))
            }
            Alphabet.MULTIPLE.ch -> {
                addIfNeed(offset, currentLine)
                memory.push(char)
                tokensArray.add(Token(Tokens.MULTIPLE, memory, offset, currentLine))
                memory.clear()
            }
            Alphabet.O.ch -> {
                memory.push(char)
                scanner.changeState(OState(scanner, tokensArray, memory))
            }
            Alphabet.A.ch -> {
                memory.push(char)
                scanner.changeState(AState(scanner, tokensArray, memory))
            }
            Alphabet.N.ch -> {
                memory.push(char)
                scanner.changeState(NState(scanner, tokensArray, memory))
            }
            Alphabet.LEFT_BRACKET.ch -> {
                addIfNeed(offset, currentLine)
                memory.push(char)
                tokensArray.add(Token(Tokens.LEFT_BRACKET, memory, offset, currentLine))
                memory.clear()
            }
            Alphabet.RIGHT_BRACKET.ch -> {
                addIfNeed(offset, currentLine)
                memory.push(char)
                tokensArray.add(Token(Tokens.RIGHT_BRACKET, memory, offset, currentLine))
                memory.clear()
            }
            Alphabet.AND.ch -> {
                addIfNeed(offset, currentLine)
                memory.push(char)
                scanner.changeState(SymbolAndState(scanner, tokensArray, memory))
            }
            Alphabet.SPACE.ch -> {
                memory.push(char)
                tokensArray.add(scanner.joinToIdentifier(memory, offset, currentLine))
            }
            Alphabet.INVERSE.ch -> {
                memory.push(char)
                tokensArray.add(Token(Tokens.INVERSE, memory, offset, currentLine))
                memory.clear()
            }
            Alphabet.OR.ch -> {
                addIfNeed(offset, currentLine)
                memory.push(char)
                scanner.changeState(SymbolOrState(scanner, tokensArray, memory))
            }
            else -> {
                memory.push(char)
            }
        }
    }

    private fun addIfNeed(offset: Int, currentLine: Int) {
        if (memory.isNotEmpty()) {
            tokensArray.add(scanner.joinToIdentifier(memory, offset, currentLine))
            memory.clear()
        }
    }
}