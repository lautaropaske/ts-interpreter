package lexer;

import tokens.Token;

import java.util.List;

public interface Lexer {
    /**
     * This is the sole purpose of the lexer.Lexer interface: to convert words into tokens.
     *
     * ASSUMPTION: words are properly separated by a whitespace. This could mean another object is passing just valid lines of text,
     * or the consumed text is properly defined.
     *
     * @param file a text file converted to string properly separated by the ' ' character (whitespace)
     * @return a list of the tokens contained in the file, in lexical order
     */
    List<Token> lex(String file);
}
