package lexer;

import javafx.util.Pair;
import tokens.Token;
import tokens.TokenFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LexerImpl implements Lexer {

    private final List<String> SYMBOL_LIST = Arrays.asList("=", "+", "-", "*", "/", "(", ")", ";", ":");
    private final List<String> DELIMITER_LIST = Arrays.asList(" ", "\n");
    private TokenFactory tokenFactory;

    public LexerImpl(){
        this.tokenFactory = new TokenFactory();
    }

    @Override
    public List<Token> tokenize(String file) {
        return mapToToken(splitPossibleTokens(file));
    }

    private List<Token> mapToToken(List<String> splatFile) {
        List<Pair<String, int[]>> assignedCoordinates = assignCoordinates(splatFile);

        return assignedCoordinates.stream()
                                  .map( pair -> tokenFactory.getTokenFor(pair.getKey(), pair.getValue()))
                                  .collect(Collectors.toList());
    }

    private List<Pair<String,int[]>> assignCoordinates(List<String> splatFile) {
        int row = 1;
        int col = 1;
        List<Pair<String, int[]>> tokensWithCoordinates = new ArrayList<>();

        for (String token: splatFile) {
            tokensWithCoordinates.add(new Pair<>(token, new int[]{row, col}));

            if(token.equals("\n")) {
                row++;
                col=1;
                continue;
            }
            col += token.length();
        }


        return tokensWithCoordinates;
    }

    /**
     * This is the function in charge of solving cases were expressions come like -> 5+ 3     instead of -> 5 + 3
     *                                                                               foo =5;                foo = 5 ;
     *                                                                               5*4 + 4                5 * 4 + 4
     * @param file the string to be splat
     * @return a properly splat string were each array item is a single, representative token
     */
    private List<String> splitPossibleTokens(String file) {
        List<String> possibleTokens = new ArrayList<>();
        List<Character> acc = new ArrayList<>();

        for (char c: file.toCharArray()) {
            if (DELIMITER_LIST.contains(String.valueOf(c)) || SYMBOL_LIST.contains(String.valueOf(c))) {         // You found a delimiter/symbol
                if(!acc.isEmpty()) {
                    possibleTokens.add(asString(acc));                  // This means you are closing an accumulated word. Add word to list
                    acc = new ArrayList<>();                            // Restart accumulator
                }
                possibleTokens.add(String.valueOf(c));                  // Then add whitespace/symbol
            } else {                                                    // If you didn't find whitespace/symbol, you found a character
                acc.add(c);                                             // Accumulate character
            }
        }

        return possibleTokens;
    }

    /**
     * Build own List<Character> to String converter to reduce dependencies
     *
     * @param characterList list of characters
     * @return string representation
     */
    private String asString(List<Character> characterList) {
        return characterList.stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining());
    }
}
