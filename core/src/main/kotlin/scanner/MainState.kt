package scanner

import java.util.*
import scanner.ifStates.*
import scanner.ifStates.ElState
import scanner.arithmeticStates.*


class MainState(override val scanner: ScannerAutomate,
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
//                addUnrecognized()
                addIfNeed(offset, page)
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
            char == Alphabet.PLUS.ch -> {
                addIfNeed(offset, page)
                memory.push(char)
                tokensArray.add(Token(Tokens.PLUS, memory, offset, page))
                memory.clear()
            }
            char == Alphabet.MINUS.ch -> {
                addIfNeed(offset, page)
                memory.push(char)
                tokensArray.add(Token(Tokens.MINUS, memory, offset, page))
                memory.clear()
            }
            char == Alphabet.LEFT_SHIFT.ch -> {
                addIfNeed(offset, page)
                memory.push(char)

                scanner.changeState(LeftShiftState(scanner, tokensArray, memory, offset, page))
            }
            char == Alphabet.RIGHT_SHIFT.ch -> {
                addIfNeed(offset, page)
                memory.push(char)
                scanner.changeState(RightShiftState(scanner, tokensArray, memory, offset, page))
            }
            char == Alphabet.EQUAL.ch -> {
                addIfNeed(offset, page)
                memory.push(char)
                scanner.changeState(EqualState(scanner, tokensArray, memory, offset, page))
            }
            char == Alphabet.NOT_EQUAL.ch -> {
                addIfNeed(offset, page)
                memory.push(char)
                scanner.changeState(NotEqualState(scanner, tokensArray, memory, offset, page))
            }
            char == Alphabet.REMAINDER.ch -> {
                addIfNeed(offset, page)
                memory.push(char)
                tokensArray.add(Token(Tokens.REMAINDER, memory, offset, page))
                memory.clear()
            }
            char == Alphabet.DIVIDER.ch -> {
                addIfNeed(offset, page)
                memory.push(char)
                scanner.changeState(DividerState(scanner, tokensArray, memory, offset, page))
            }
            char == Alphabet.MULTIPLE.ch -> {
                addIfNeed(offset, page)
                memory.push(char)
                tokensArray.add(Token(Tokens.MULTIPLE, memory, offset, page))
                memory.clear()
            }
            char == Alphabet.O.ch -> {
                memory.push(char)
                scanner.changeState(OState(scanner, tokensArray, memory, offset, page))
            }
            char == Alphabet.A.ch -> {
                memory.push(char)
                scanner.changeState(AState(scanner, tokensArray, memory, offset, page))
            }
            char == Alphabet.N.ch -> {
                memory.push(char)
                scanner.changeState(NState(scanner, tokensArray, memory, offset, page))
            }
//            Alphabet.LEFT_BRACKET.ch -> {
//                addIfNeed(offset, page)
//                memory.push(char)
//                tokensArray.add(Token(Tokens.LEFT_BRACKET, memory, offset, page))
//                memory.clear()
//            }
//            Alphabet.RIGHT_BRACKET.ch -> {
//                addIfNeed(offset, page)
//                memory.push(char)
//                tokensArray.add(Token(Tokens.RIGHT_BRACKET, memory, offset, page))
//                memory.clear()
//            }
            char == Alphabet.AND.ch -> {
                addIfNeed(offset, page)
                memory.push(char)
                scanner.changeState(SymbolAndState(scanner, tokensArray, memory, offset, page))
            }
            char == Alphabet.SPACE.ch -> {
                memory.push(char)
                tokensArray.add(scanner.joinToIdentifier(memory, offset, page))
            }
            char == Alphabet.INVERSE.ch -> {
                memory.push(char)
                tokensArray.add(Token(Tokens.INVERSE, memory, offset, page))
                memory.clear()
            }
            char == Alphabet.OR.ch -> {
                addIfNeed(offset, page)
                memory.push(char)
                scanner.changeState(SymbolOrState(scanner, tokensArray, memory, offset, page))
            }
            char == Alphabet.COLON.ch -> {
                addIfNeed(offset, page)
                memory.push(char)
                tokensArray.add(Token(Tokens.COLON, memory, offset, page))
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

    private fun addIfNeed(offset: Int, page: Int) {
        if (memory.isNotEmpty()) {
            tokensArray.add(scanner.joinToIdentifier(memory, offset - 1, page))
            memory.clear()
        }
    }
}