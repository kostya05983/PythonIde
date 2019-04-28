package scanner.ariphmeticScanAutomat

import java.util.*
import scanner.Alphabet
import scanner.Token
import scanner.Tokens

/**
 * From this state we decide this is just identifier an or we continue
 * to think that it's and
 * @author kostya05983
 */
class ANState(override val scanner: ArithmeticScanner,
              override val tokensArray: LinkedList<Token>,
              override val memory: Stack<Char>) : State {

    override fun parse(char: Char, currentLine: Int, offset: Int) {
        memory.push(char)
        when (char) {
            Alphabet.D.ch -> {
                scanner.changeState(ANDState(scanner, tokensArray, memory))
            }
            Alphabet.SPACE.ch -> {
                memory.push(char)
                tokensArray.add(scanner.joinToIdentifier(memory, offset, currentLine))
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            Alphabet.PLUS.ch -> {
                tokensArray.add(scanner.joinToIdentifier(memory, offset, currentLine))
                memory.push(char)
                tokensArray.add(Token(Tokens.PLUS, memory, offset, currentLine))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            Alphabet.MINUS.ch -> {
                tokensArray.add(scanner.joinToIdentifier(memory, offset, currentLine))
                memory.push(char)
                tokensArray.add(Token(Tokens.MINUS, memory, offset, currentLine))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            Alphabet.LEFT_SHIFT.ch -> {
                tokensArray.add(scanner.joinToIdentifier(memory, offset, currentLine))
                memory.push(char)
                scanner.changeState(LeftShiftState(scanner, tokensArray, memory))
            }
            Alphabet.RIGHT_SHIFT.ch -> {
                tokensArray.add(scanner.joinToIdentifier(memory, offset, currentLine))
                memory.push(char)
                scanner.changeState(RightShiftState(scanner, tokensArray, memory))
            }
            Alphabet.EQUAL.ch -> {
                tokensArray.add(scanner.joinToIdentifier(memory, offset, currentLine))
                memory.push(char)
                scanner.changeState(EqualState(scanner, tokensArray, memory))
            }
            Alphabet.NOT_EQUAL.ch -> {
                tokensArray.add(scanner.joinToIdentifier(memory, offset, currentLine))
                memory.push(char)
                scanner.changeState(NotEqualState(scanner, tokensArray, memory))
            }
            Alphabet.REMAINDER.ch -> {
                tokensArray.add(scanner.joinToIdentifier(memory, offset, currentLine))
                memory.push(char)
                tokensArray.add(Token(Tokens.REMAINDER, memory, offset, currentLine))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            Alphabet.DIVIDER.ch -> {
                tokensArray.add(scanner.joinToIdentifier(memory, offset, currentLine))
                memory.push(char)
                scanner.changeState(DividerState(scanner, tokensArray, memory))
            }
            Alphabet.MULTIPLE.ch -> {
                tokensArray.add(scanner.joinToIdentifier(memory, offset, currentLine))
                memory.push(char)
                tokensArray.add(Token(Tokens.MULTIPLE, memory, offset, currentLine))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            Alphabet.INVERSE.ch -> {
                tokensArray.add(scanner.joinToIdentifier(memory, offset, currentLine))
                memory.push(char)
                tokensArray.add(Token(Tokens.INVERSE, memory, offset, currentLine))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            Alphabet.AND.ch -> {
                tokensArray.add(scanner.joinToIdentifier(memory, offset, currentLine))
                memory.push(char)
                scanner.changeState(DoubleSymbolAndState(scanner, tokensArray, memory))
            }
            Alphabet.OR.ch -> {
                tokensArray.add(scanner.joinToIdentifier(memory, offset, currentLine))
                memory.push(char)
                scanner.changeState(DoubleSymbolOrState(scanner, tokensArray, memory))
            }
            Alphabet.LEFT_BRACKET.ch -> {
                tokensArray.add(scanner.joinToIdentifier(memory, offset, currentLine))
                memory.push(char)
                tokensArray.add(Token(Tokens.LEFT_BRACKET, memory, offset, currentLine))
                memory.clear()
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            Alphabet.RIGHT_BRACKET.ch -> {
                tokensArray.add(scanner.joinToIdentifier(memory, offset, currentLine))
                memory.push(char)
                tokensArray.add(Token(Tokens.RIGHT_BRACKET, memory, offset, currentLine))
                memory.clear()

                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
            else -> {
                memory.push(char)
                scanner.changeState(MainState(scanner, tokensArray, memory))
            }
        }
    }
}