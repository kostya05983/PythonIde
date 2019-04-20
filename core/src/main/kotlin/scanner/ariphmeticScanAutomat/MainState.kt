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
                //todo next sate
            }
            Alphabet.RIGHT_SHIFT.ch -> {
                //todo next state
            }
            Alphabet.EQUAL.ch -> {
                //todo next state
            }
            Alphabet.NOT_EQUAL.ch -> {
                //todo next state
            }
            Alphabet.REMAINDER.ch -> {
                tokensArray.add(Token(Tokens.REMAINDER, Tokens.REMAINDER.literal))
            }
            Alphabet.DIVIDER.ch -> {
                //todo next state
            }
            Alphabet.MULTIPLE.ch -> {
                tokensArray.add(Token(Tokens.MULTIPLE, Tokens.MULTIPLE.literal))
            }
            Alphabet.O.ch -> {
                //todo next state
            }
            Alphabet.A.ch -> {
                //todo next state
            }
            Alphabet.N.ch -> {
                //todo next state
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