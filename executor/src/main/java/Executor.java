import lexer.Lexer;
import lexer.LexerImpl;
import parser.Parser;
import parser.ParserImpl;
import interpreter.Interpreter;
import interpreter.InterpreterImpl;

public class Executor {

    private Lexer lexer;
    private Parser parser;
    private Interpreter interpreter;

    public Executor(){
        this.lexer = new LexerImpl();
        this.parser = new ParserImpl();
        this.interpreter = new InterpreterImpl();
    }

    public void execute(String file){
        interpreter.interpret(parser.parse(lexer.lex(file)));
    }
}
