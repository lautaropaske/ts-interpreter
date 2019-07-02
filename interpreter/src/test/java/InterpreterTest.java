import ast.ASTNode;
import interpreter.Interpreter;
import interpreter.InterpreterImpl;
import interpreter.exceptions.InterpreterException;
import lexer.Lexer;
import lexer.LexerImpl;
import org.junit.jupiter.api.Test;
import parser.Parser;
import parser.ParserImpl;

public class InterpreterTest {
    private Lexer lexer = new LexerImpl();
    private Parser parser = new ParserImpl();
    private Interpreter interpreter = new InterpreterImpl();

    @Test
    public void pass_interpretDeclarationAssignationAndPrint() {
        String line = "let foo : number = 5;\n" +
                      "print(foo + 3);";

        ASTNode ast = this.parser.parse(lexer.lex(line));

        interpreter.interpret(ast);

        String line2 = "let foo : string = 'valid assignation';\n" +
                       "print(foo + 3);";

        ASTNode ast2 = this.parser.parse(lexer.lex(line2));

        interpreter.interpret(ast2);
    }

    @Test
    public void fail_invalidDeclarationAssignation() {
        String line = "let foo: number = 'this is a string';\n";

        ASTNode ast = this.parser.parse(lexer.lex(line));
        try {
            interpreter.interpret(ast);
        } catch (InterpreterException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void fail_invalidStringOperations() {
        String line = "print('a string' - 3);";

        ASTNode ast = this.parser.parse(lexer.lex(line));

        try {
            interpreter.interpret(ast);
        } catch (InterpreterException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void pass_binaryOperations() {
        String line = "let foo : number = 5 + 15;\n" + // 20
                      "let bar : number = -0.5;\n" +
                      "print(\"The result is: \" + foo * bar / bar - 10 + 1);";  // 20 * -0.5 / -0.5 - 10 + 1

        ASTNode ast = this.parser.parse(lexer.lex(line));

        interpreter.interpret(ast);
    }

    @Test
    public void fail_printStatement() {
        String line = "let bar : string = -0.5;\n" +
                      "print(\"The result is: \" + bar);";  // 20 * -0.5 / -0.5 - 10 + 1

        ASTNode ast = this.parser.parse(lexer.lex(line));

        try {
            interpreter.interpret(ast);
        } catch (InterpreterException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void pass_hardTest() {
        String line = "let foo: string=\"foo string: \"; let bar :number; \n " +
                      "print(foo);" +
                      "bar = 10/2 + 5; \n" +
                      "print(foo + bar + \' - baz\'); \n";

        ASTNode ast = this.parser.parse(lexer.lex(line));

        interpreter.interpret(ast);
    }
}
