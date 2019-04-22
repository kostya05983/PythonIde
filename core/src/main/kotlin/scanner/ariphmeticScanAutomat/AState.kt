package scanner.ariphmeticScanAutomat

import java.util.*
import scanner.Alphabet
import scanner.Token
import scanner.Tokens

/**
 * From this state decide we think that it is and or identifier
 * @author kostya05983
 */
class AState(override val scanner: ArithmeticScanner,
             override val tokensArray: LinkedList<Token>,
             override val memory: Stack<Char>) : State {
    override fun parse(char: Char) {
        when (char) {
            Alphabet.N.ch -> {
                memory.push(char)
                scanner.changeState(ANState(scanner, tokensArray, memory))
            }
            Alphabet.SPACE.ch -> {
                tokensArray.add(Token(Tokens.IDENTIFIER, memory.joinToString(separator = "")))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            Alphabet.PLUS.ch -> {
                tokensArray.add(Token(Tokens.IDENTIFIER, memory.joinToString(separator = "")))
                memory.clear()
                tokensArray.add(Token(Tokens.PLUS, Tokens.PLUS.literal))
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            Alphabet.MINUS.ch -> {
                tokensArray.add(Token(Tokens.IDENTIFIER, memory.joinToString(separator = "")))
                memory.clear()
                tokensArray.add(Token(Tokens.MINUS, Tokens.MINUS.literal))
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            Alphabet.LEFT_SHIFT.ch -> {
                tokensArray.add(Token(Tokens.IDENTIFIER, memory.joinToString(separator = "")))
                memory.clear()
                memory.push(char)
                scanner.changeState(LeftShiftState(scanner, tokensArray, memory))
            }
            Alphabet.RIGHT_SHIFT.ch -> {
                tokensArray.add(Token(Tokens.IDENTIFIER, memory.joinToString(separator = "")))
                memory.clear()
                memory.push(char)
                scanner.changeState(RightShiftState(scanner, tokensArray, memory))
            }
            Alphabet.EQUAL.ch -> {
                tokensArray.add(Token(Tokens.IDENTIFIER, memory.joinToString(separator = "")))
                memory.clear()
                memory.push(char)
                scanner.changeState(EqualState(scanner, tokensArray, memory))
            }
            Alphabet.NOT_EQUAL.ch -> {
                tokensArray.add(Token(Tokens.IDENTIFIER, memory.joinToString(separator = "")))
                memory.clear()
                memory.push(char)
                scanner.changeState(NotEqualState(scanner, tokensArray, memory))
            }
            Alphabet.REMAINDER.ch -> {
                tokensArray.add(Token(Tokens.IDENTIFIER, memory.joinToString(separator = "")))
                memory.clear()
                tokensArray.add(Token(Tokens.REMAINDER, Tokens.REMAINDER.literal))
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            Alphabet.DIVIDER.ch -> {
                tokensArray.add(Token(Tokens.IDENTIFIER, memory.joinToString(separator = "")))
                memory.clear()
                memory.push(char)
                scanner.changeState(DividerState(scanner, tokensArray, memory))
            }
            Alphabet.MULTIPLE.ch -> {
                tokensArray.add(Token(Tokens.IDENTIFIER, memory.joinToString(separator = "")))
                memory.clear()
                tokensArray.add(Token(Tokens.MULTIPLE, Tokens.MULTIPLE.literal))
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            Alphabet.INVERSE.ch -> {
                tokensArray.add(Token(Tokens.IDENTIFIER, memory.joinToString(separator = "")))
                memory.clear()
                tokensArray.add(Token(Tokens.INVERSE, Tokens.INVERSE.literal))
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            Alphabet.AND.ch -> {
                tokensArray.add(Token(Tokens.IDENTIFIER, memory.joinToString(separator = "")))
                memory.clear()
                memory.push(char)
                scanner.changeState(DoubleSymbolAndState(scanner, tokensArray, memory))
            }
            Alphabet.OR.ch -> {
                tokensArray.add(Token(Tokens.IDENTIFIER, memory.joinToString(separator = "")))
                memory.clear()
                memory.push(char)
                scanner.changeState(DoubleSymbolOrState(scanner, tokensArray, memory))
            }
            Alphabet.LEFT_BRACKET.ch -> {
                tokensArray.add(Token(Tokens.IDENTIFIER, memory.joinToString(separator = "")))
                memory.clear()
                tokensArray.add(Token(Tokens.LEFT_BRACKET, Tokens.LEFT_BRACKET.literal))
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            Alphabet.RIGHT_BRACKET.ch -> {
                tokensArray.add(Token(Tokens.IDENTIFIER, memory.joinToString(separator = "")))
                memory.clear()
                tokensArray.add(Token(Tokens.RIGHT_BRACKET, Tokens.RIGHT_BRACKET.literal))
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            else -> {
                memory.push(char)
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
        }
    }
}