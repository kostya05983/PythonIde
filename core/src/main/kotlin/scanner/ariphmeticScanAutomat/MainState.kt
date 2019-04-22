package scanner.ariphmeticScanAutomat

import scanner.Alphabet
import scanner.Token
import scanner.Tokens
import java.util.*

class MainState(override val scanner: ArithmeticScanner,
                override val tokensArray: LinkedList<Token>,
                override val memory: Stack<Char>) : State {
    //тип пользовтаеля, идентификатор пользователя
    override fun parse(char: Char) {
        when (char) {
            Alphabet.PLUS.ch -> {
                addIfNeed()
                memory.push(char)
                tokensArray.add(Token(Tokens.PLUS, Tokens.PLUS.literal))
            }
            Alphabet.MINUS.ch -> {
                addIfNeed()
                memory.push(char)
                tokensArray.add(Token(Tokens.MINUS, Tokens.MINUS.literal))
            }
            Alphabet.LEFT_SHIFT.ch -> {
                addIfNeed()
                memory.push(char)
                scanner.changeState(LeftShiftState(scanner, tokensArray, memory))
            }
            Alphabet.RIGHT_SHIFT.ch -> {
                addIfNeed()
                memory.push(char)
                scanner.changeState(RightShiftState(scanner, tokensArray, memory))
            }
            Alphabet.EQUAL.ch -> {
                addIfNeed()
                memory.push(char)
                scanner.changeState(EqualState(scanner, tokensArray, memory))
            }
            Alphabet.NOT_EQUAL.ch -> {
                addIfNeed()
                memory.push(char)
                scanner.changeState(NotEqualState(scanner, tokensArray, memory))
            }
            Alphabet.REMAINDER.ch -> {
                addIfNeed()
                memory.push(char)
                tokensArray.add(Token(Tokens.REMAINDER, Tokens.REMAINDER.literal))
            }
            Alphabet.DIVIDER.ch -> {
                addIfNeed()
                memory.push(char)
                scanner.changeState(DividerState(scanner, tokensArray, memory))
            }
            Alphabet.MULTIPLE.ch -> {
                addIfNeed()
                memory.push(char)
                tokensArray.add(Token(Tokens.MULTIPLE, Tokens.MULTIPLE.literal))
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
                addIfNeed()
                memory.push(char)
                tokensArray.add(Token(Tokens.LEFT_BRACKET, Tokens.LEFT_BRACKET.literal))
            }
            Alphabet.RIGHT_BRACKET.ch -> {
                addIfNeed()
                memory.push(char)
                tokensArray.add(Token(Tokens.RIGHT_BRACKET, Tokens.RIGHT_BRACKET.literal))
            }
            Alphabet.AND.ch -> {
                addIfNeed()
                memory.push(char)
                scanner.changeState(SymbolAndState(scanner, tokensArray, memory))
            }
            Alphabet.SPACE.ch -> {
                tokensArray.add(Token(Tokens.IDENTIFIER, memory.joinToString(separator = "")))
                memory.clear()
            }
            else -> {
                memory.push(char)
            }
        }
    }

    private fun addIfNeed() {
        if (memory.isNotEmpty()) {
            tokensArray.add(Token(Tokens.IDENTIFIER, memory.joinToString(separator = "")))
            memory.clear()
        }

    }
}