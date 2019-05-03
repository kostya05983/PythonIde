package scanner.arithmeticStates

import scanner.*
import java.util.*

/**
 * From this state we decide this is just identifier an or we continue
 * to think that it's and
 * @author kostya05983
 */
class ANState(override val scanner: ScannerAutomate,
              override val tokensArray: LinkedList<Token>,
              override val memory: Stack<Char>,
              override var offset: Int,
              override var page: Int) : State {

    override fun parse(char: Char) {
        memory.push(char)
        when (char) {
            Alphabet.D.ch -> {
                scanner.changeState(ANDState(scanner, tokensArray, memory, offset, page))
            }
            Alphabet.SPACE.ch -> {
                memory.push(char)
                tokensArray.add(scanner.joinToIdentifier(memory, offset, page))
                scanner.changeState(ArithmeticMainState(scanner, tokensArray, memory, offset, page))
            }
            Alphabet.PLUS.ch -> {
                tokensArray.add(scanner.joinToIdentifier(memory, offset, page))
                memory.push(char)
                tokensArray.add(Token(Tokens.PLUS, memory, offset, page))
                memory.clear()
                scanner.changeState(ArithmeticMainState(scanner, tokensArray, memory, offset, page))
            }
            Alphabet.MINUS.ch -> {
                tokensArray.add(scanner.joinToIdentifier(memory, offset, page))
                memory.push(char)
                tokensArray.add(Token(Tokens.MINUS, memory, offset, page))
                memory.clear()
                scanner.changeState(ArithmeticMainState(scanner, tokensArray, memory, offset, page))
            }
            Alphabet.LEFT_SHIFT.ch -> {
                tokensArray.add(scanner.joinToIdentifier(memory, offset, page))
                memory.push(char)
                scanner.changeState(LeftShiftState(scanner, tokensArray, memory, offset, page))
            }
            Alphabet.RIGHT_SHIFT.ch -> {
                tokensArray.add(scanner.joinToIdentifier(memory, offset, page))
                memory.push(char)
                scanner.changeState(RightShiftState(scanner, tokensArray, memory, offset, page))
            }
            Alphabet.EQUAL.ch -> {
                tokensArray.add(scanner.joinToIdentifier(memory, offset, page))
                memory.push(char)
                scanner.changeState(EqualState(scanner, tokensArray, memory, offset, page))
            }
            Alphabet.NOT_EQUAL.ch -> {
                tokensArray.add(scanner.joinToIdentifier(memory, offset, page))
                memory.push(char)
                scanner.changeState(NotEqualState(scanner, tokensArray, memory, offset, page))
            }
            Alphabet.REMAINDER.ch -> {
                tokensArray.add(scanner.joinToIdentifier(memory, offset, page))
                memory.push(char)
                tokensArray.add(Token(Tokens.REMAINDER, memory, offset, page))
                memory.clear()
                scanner.changeState(ArithmeticMainState(scanner, tokensArray, memory, offset, page))
            }
            Alphabet.DIVIDER.ch -> {
                tokensArray.add(scanner.joinToIdentifier(memory, offset, page))
                memory.push(char)
                scanner.changeState(DividerState(scanner, tokensArray, memory, offset, page))
            }
            Alphabet.MULTIPLE.ch -> {
                tokensArray.add(scanner.joinToIdentifier(memory, offset, page))
                memory.push(char)
                tokensArray.add(Token(Tokens.MULTIPLE, memory, offset, page))
                memory.clear()
                scanner.changeState(ArithmeticMainState(scanner, tokensArray, memory, offset, page))
            }
            Alphabet.INVERSE.ch -> {
                tokensArray.add(scanner.joinToIdentifier(memory, offset, page))
                memory.push(char)
                tokensArray.add(Token(Tokens.INVERSE, memory, offset, page))
                memory.clear()
                scanner.changeState(ArithmeticMainState(scanner, tokensArray, memory, offset, page))
            }
            Alphabet.AND.ch -> {
                tokensArray.add(scanner.joinToIdentifier(memory, offset, page))
                memory.push(char)
                scanner.changeState(SymbolAndState(scanner, tokensArray, memory, offset, page))
            }
            Alphabet.OR.ch -> {
                tokensArray.add(scanner.joinToIdentifier(memory, offset, page))
                memory.push(char)
                scanner.changeState(SymbolOrState(scanner, tokensArray, memory, offset, page))
            }
            Alphabet.LEFT_BRACKET.ch -> {
                tokensArray.add(scanner.joinToIdentifier(memory, offset, page))
                memory.push(char)
                tokensArray.add(Token(Tokens.LEFT_BRACKET, memory, offset, page))
                memory.clear()
                scanner.changeState(ArithmeticMainState(scanner, tokensArray, memory, offset, page))
            }
            Alphabet.RIGHT_BRACKET.ch -> {
                tokensArray.add(scanner.joinToIdentifier(memory, offset, page))
                memory.push(char)
                tokensArray.add(Token(Tokens.RIGHT_BRACKET, memory, offset, page))
                memory.clear()
                scanner.changeState(ArithmeticMainState(scanner, tokensArray, memory, offset, page))
            }
            else -> {
                memory.push(char)
                scanner.changeState(ArithmeticMainState(scanner, tokensArray, memory, offset, page))
            }
        }
    }
}