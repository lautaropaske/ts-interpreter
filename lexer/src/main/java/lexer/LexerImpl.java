package lexer;

import exceptions.LexerException;
import javafx.util.Pair;
import tokens.Token;
import tokens.TokenFactory;
import utils.Definitions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LexerImpl implements Lexer {

    private final List<String> SYMBOL_LIST = Arrays.asList("=", "+", "-", "*", "/", "(", ")", ";", ":");
    private final List<String> DELIMITER_LIST = Arrays.asList(" ", "\n");
    private final List<String> STRING_SYMBOL_LIST = Arrays.asList("\"", "\'");
    private TokenFactory tokenFactory;

    public LexerImpl(){
        this.tokenFactory = new TokenFactory();
    }

    @Override
    public List<Token> lex(String file) {
        return mapToToken(splitPossibleTokens(file));
    }

    private List<Token> mapToToken(List<String> possibleTokens) {
        if(possibleTokens.contains("-")) possibleTokens = joinNegatives(possibleTokens);
        List<Pair<String, int[]>> assignedCoordinates = assignCoordinates(possibleTokens);

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
        String stringSymbol = null;

        for (char c: file.toCharArray()) {
            String character = String.valueOf(c);

            if ((DELIMITER_LIST.contains(character) || SYMBOL_LIST.contains(character)) && stringSymbol == null) {      // You found a delimiter/symbol
                if(!acc.isEmpty()) {
                    possibleTokens.add(asString(acc));                                                                  // This means you are closing an accumulated word. Add word to list
                    acc = new ArrayList<>();                                                                            // Restart accumulator
                }
                possibleTokens.add(character);                                                                          // Then add delimiter/symbol
            } else {                                                                                                    // If you didn't find delimiter/symbol, you found a character
                if(STRING_SYMBOL_LIST.contains(character)){                                                             // If you found a string delimiter, you might be entering a string literal
                    if(stringSymbol == null) {
                        stringSymbol = character;                                                                       // If you were not already inside a string, declare initialization
                        if(!acc.isEmpty()) {
                            possibleTokens.add(asString(acc));                                                              // Add word to list and restart accumulator
                            acc = new ArrayList<>();
                        }
                    }
                    else {
                        if (stringSymbol.equals(character)) stringSymbol = null;                                        // Else, you are trying to close a string (only valid if you're using the same delimiter)
                        else acc.add(c);                                                                                     // or you're adding a character to the accumulated string
                    }
                }
                acc.add(c);                                                                                             // Accumulate character
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

    /**
     * '-' a.k.a Minus symbol is special.
     *
     * This helper method joins minus and a number if no whitespace is in between
     * @param possibleTokens splat file into possible tokens
     * @return splat file with joined negatives
     */
    private List<String> joinNegatives(List<String> possibleTokens) {
        List<String> joinedNegatives = new ArrayList<>();

        for (int i = 0; i < possibleTokens.size(); i++) {
            String token = possibleTokens.get(i);

            if(token.matches("-")){
                String nextToken = possibleTokens.get(i+1);
                if(nextToken.matches(Definitions.NUM_LITERAL_REGEX)) {
                    joinedNegatives.add(token + nextToken);
                    i++; continue;
                }
            }

            joinedNegatives.add(token);
        }

        return joinedNegatives;
    }
}
