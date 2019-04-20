package scanner.ariphmeticScanAutomat

import scanner.Alphabet
import scanner.Token
import scanner.Tokens
import java.util.*

class MainState(override val scanner: ArithmeticScanner,
                override val tokensArray: LinkedList<Token>,
                override val memory: Stack<Char>) : State {
    override fun parse(char: Char) {
        when (char) {
            Alphabet.PLUS.ch -> {
                tokensArray.add(Token(Tokens.PLUS, Tokens.PLUS.literal))
            }
            Alphabet.MINUS.ch -> {
                tokensArray.add(Token(Tokens.MINUS, Tokens.MINUS.literal))
            }
            Alphabet.LEFT_SHIFT.ch -> {
                scanner.changeState(LeftShiftState(scanner, tokensArray, memory))
            }
            Alphabet.RIGHT_SHIFT.ch -> {
                scanner.changeState(RightShiftState(scanner, tokensArray, memory))
            }
            Alphabet.EQUAL.ch -> {
                scanner.changeState(EqualState(scanner, tokensArray, memory))
            }
            Alphabet.NOT_EQUAL.ch -> {
                scanner.changeState(NotEqualState(scanner, tokensArray, memory))
            }
            Alphabet.REMAINDER.ch -> {
                tokensArray.add(Token(Tokens.REMAINDER, Tokens.REMAINDER.literal))
            }
            Alphabet.DIVIDER.ch -> {
                scanner.changeState(DividerState(scanner, tokensArray, memory))
            }
            Alphabet.MULTIPLE.ch -> {
                tokensArray.add(Token(Tokens.MULTIPLE, Tokens.MULTIPLE.literal))
            }
            Alphabet.O.ch -> {
                scanner.changeState(OState(scanner, tokensArray, memory))
            }
            Alphabet.A.ch -> {
                scanner.changeState(AState(scanner, tokensArray, memory))
            }
            Alphabet.N.ch -> {
                scanner.changeState(NState(scanner, tokensArray, memory))
            }
            Alphabet.LEFT_BRACKET.ch -> {
                tokensArray.add(Token(Tokens.LEFT_BRACKET, Tokens.LEFT_BRACKET.literal))
            }
            Alphabet.RIGHT_BRACKET.ch -> {
                tokensArray.add(Token(Tokens.RIGHT_BRACKET, Tokens.RIGHT_BRACKET.literal))
            }
        }
    }
}