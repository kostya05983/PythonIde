package scanner.ariphmeticScanAutomat

import scanner.Token
import java.util.*
import scanner.Alphabet
import scanner.Tokens

/**
 * from this state we dicied this is continue of or or this is identifier
 * or continue of identifier
 * @author kostya05983
 */
class OState(override val scanner: ArithmeticScanner,
             override val tokensArray: LinkedList<Token>,
             override val memory: Stack<Char>) : State {
    override fun parse(char: Char, currentLine: Int, offset: Int) {
        memory.push(char)
        when (char) {
            Alphabet.R.ch -> {
                scanner.changeState(ORState(scanner, tokensArray, memory))
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
                scanner.changeState(SymbolAndState(scanner, tokensArray, memory))
            }
            Alphabet.OR.ch -> {
                tokensArray.add(scanner.joinToIdentifier(memory, offset, currentLine))
                memory.push(char)
                scanner.changeState(SymbolOrState(scanner, tokensArray, memory))
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