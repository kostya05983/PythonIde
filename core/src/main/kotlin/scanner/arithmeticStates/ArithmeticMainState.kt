package scanner.arithmeticStates

import scanner.*
import java.util.*
import scanner.ifStates.ConditionState

class ArithmeticMainState(override val scanner: ScannerAutomate,
                          override val tokensArray: LinkedList<Token>,
                          override val memory: Stack<Char>,
                          override var offset: Int,
                          override var page: Int) : State {

    //тип пользовтаеля, идентификатор пользователя
    override fun parse(char: Char) {
        when (char) {
            Alphabet.PLUS.ch -> {
                addIfNeed(offset, page)
                memory.push(char)
                tokensArray.add(Token(Tokens.PLUS, memory, offset, page))
                memory.clear()
            }
            Alphabet.MINUS.ch -> {
                addIfNeed(offset, page)
                memory.push(char)
                tokensArray.add(Token(Tokens.MINUS, memory, offset, page))
                memory.clear()
            }
            Alphabet.LEFT_SHIFT.ch -> {
                addIfNeed(offset, page)
                memory.push(char)

                scanner.changeState(LeftShiftState(scanner, tokensArray, memory, offset, page))
            }
            Alphabet.RIGHT_SHIFT.ch -> {
                addIfNeed(offset, page)
                memory.push(char)
                scanner.changeState(RightShiftState(scanner, tokensArray, memory, offset, page))
            }
            Alphabet.EQUAL.ch -> {
                addIfNeed(offset, page)
                memory.push(char)
                scanner.changeState(EqualState(scanner, tokensArray, memory, offset, page))
            }
            Alphabet.NOT_EQUAL.ch -> {
                addIfNeed(offset, page)
                memory.push(char)
                scanner.changeState(NotEqualState(scanner, tokensArray, memory, offset, page))
            }
            Alphabet.REMAINDER.ch -> {
                addIfNeed(offset, page)
                memory.push(char)
                tokensArray.add(Token(Tokens.REMAINDER, memory, offset, page))
                memory.clear()
            }
            Alphabet.DIVIDER.ch -> {
                addIfNeed(offset, page)
                memory.push(char)
                scanner.changeState(DividerState(scanner, tokensArray, memory, offset, page))
            }
            Alphabet.MULTIPLE.ch -> {
                addIfNeed(offset, page)
                memory.push(char)
                tokensArray.add(Token(Tokens.MULTIPLE, memory, offset, page))
                memory.clear()
            }
            Alphabet.O.ch -> {
                memory.push(char)
                scanner.changeState(OState(scanner, tokensArray, memory, offset, page))
            }
            Alphabet.A.ch -> {
                memory.push(char)
                scanner.changeState(AState(scanner, tokensArray, memory, offset, page))
            }
            Alphabet.N.ch -> {
                memory.push(char)
                scanner.changeState(NState(scanner, tokensArray, memory, offset, page))
            }
            Alphabet.LEFT_BRACKET.ch -> {
                addIfNeed(offset, page)
                memory.push(char)
                tokensArray.add(Token(Tokens.LEFT_BRACKET, memory, offset, page))
                memory.clear()
            }
            Alphabet.RIGHT_BRACKET.ch -> {
                addIfNeed(offset, page)
                memory.push(char)
                tokensArray.add(Token(Tokens.RIGHT_BRACKET, memory, offset, page))
                memory.clear()
            }
            Alphabet.AND.ch -> {
                addIfNeed(offset, page)
                memory.push(char)
                scanner.changeState(SymbolAndState(scanner, tokensArray, memory, offset, page))
            }
            Alphabet.SPACE.ch -> {
                memory.push(char)
                tokensArray.add(scanner.joinToIdentifier(memory, offset, page))
            }
            Alphabet.INVERSE.ch -> {
                memory.push(char)
                tokensArray.add(Token(Tokens.INVERSE, memory, offset, page))
                memory.clear()
            }
            Alphabet.OR.ch -> {
                addIfNeed(offset, page)
                memory.push(char)
                scanner.changeState(SymbolOrState(scanner, tokensArray, memory, offset, page))
            }
            Alphabet.COLON.ch -> {
                addIfNeed(offset, page)
                memory.push(char)
                tokensArray.add(Token(Tokens.COLON, memory, offset, page))

                scanner.changeState(ConditionState(scanner, tokensArray, memory, offset, page))
            }
            else -> {
                memory.push(char)
            }
        }
    }

    private fun addIfNeed(offset: Int, page: Int) {
        if (memory.isNotEmpty()) {
            tokensArray.add(scanner.joinToIdentifier(memory, offset-1, page))
            memory.clear()
        }
    }
}