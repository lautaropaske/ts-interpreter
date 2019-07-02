package exceptions;


public class ParserException extends RuntimeException {
    public ParserException(String msg, int row, int col){
        super("Parser exception: " + msg + " at " + row + ":" + col);
    }
}
