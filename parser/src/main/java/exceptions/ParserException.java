package exceptions;


public class ParserException extends RuntimeException {
    public ParserException(String msg, int row, int col){
        super(msg + "at " + row + ":" + col);
    }
}
