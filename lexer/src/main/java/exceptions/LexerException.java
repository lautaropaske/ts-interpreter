package exceptions;

public class LexerException extends RuntimeException {
    public LexerException(String msg, int row, int col){
        super("Lexer exception:" + msg + " at " + row + ":" + col);
    }
}
