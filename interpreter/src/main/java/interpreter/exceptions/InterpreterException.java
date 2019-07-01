package interpreter.exceptions;

public class InterpreterException extends RuntimeException{
    public InterpreterException(String msg) {
        super("Interpreter exception:" + msg);
    }
}
