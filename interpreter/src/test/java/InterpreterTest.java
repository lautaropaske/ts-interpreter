import ast.ASTNode;
import interpreter.Interpreter;
import interpreter.InterpreterImpl;
import interpreter.exceptions.InterpreterException;
import lexer.Lexer;
import lexer.LexerImpl;
import org.junit.jupiter.api.Test;
import parser.Parser;
import parser.ParserImpl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class InterpreterTest {
    private Lexer lexer = new LexerImpl();
    private Parser parser = new ParserImpl();
    private Interpreter interpreter = new InterpreterImpl();

    @Test
    public void pass_interpretDeclarationAssignationAndPrint() {
        String line = "let foo : number = 5;\n" +
                      "print(foo + 3);";

        ASTNode ast = this.parser.parse(lexer.lex(line));

        System.out.println("\n\n### EXPECTING SUCCESS ###");
        interpreter.interpret(ast);

        String line2 = "let foo : string = 'valid assignation';\n" +
                       "print(foo + 3);";

        ASTNode ast2 = this.parser.parse(lexer.lex(line2));

        System.out.println("\n\n### EXPECTING SUCCESS ###");
        interpreter.interpret(ast2);
    }

    @Test
    public void fail_invalidDeclarationAssignation() {
        String line = "let foo: number = 'this is a string';\n";

        ASTNode ast = this.parser.parse(lexer.lex(line));
        try {
            System.out.println("\n\n### EXPECTING AN EXCEPTION ###");
            interpreter.interpret(ast);
        } catch (InterpreterException ex) {
            System.out.println("### EXPECTED EXCEPTION ###\n\n");
            System.out.println(ex.getMessage());
            System.out.println("\n");
        }
    }

    @Test
    public void fail_invalidStringOperations() {
        String line = "print('a string' - 3);";

        ASTNode ast = this.parser.parse(lexer.lex(line));

        try {
            System.out.println("\n\n### EXPECTING AN EXCEPTION ###");
            interpreter.interpret(ast);
        } catch (InterpreterException ex) {
            System.out.println("### EXPECTED EXCEPTION ###\n\n");
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void pass_binaryOperations() {
        String line = "let foo : number = 5 + 15;\n" + // 20
                      "let bar : number = -0.5;\n" +
                      "print(\"The result is: \" + foo * bar / bar - 10 + 1);";  // 20 * -0.5 / -0.5 - 10 + 1

        ASTNode ast = this.parser.parse(lexer.lex(line));

        System.out.println("\n\n### EXPECTING SUCCESS ###");
        interpreter.interpret(ast);
    }

    @Test
    public void fail_printStatement() {
        String line = "let bar : string = -0.5;\n" +
                      "print(\"The result is: \" + bar);";  // 20 * -0.5 / -0.5 - 10 + 1

        ASTNode ast = this.parser.parse(lexer.lex(line));

        try {
            System.out.println("\n\n### EXPECTING AN EXCEPTION ###");
            interpreter.interpret(ast);
        } catch (InterpreterException ex) {
            System.out.println("### EXPECTED EXCEPTION ###\n\n");
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void pass_hardTest() {
        String line = "let bar: number = 5;\n" +
                      "let foo: string = 'foo string: ';\n" +
                      "print(\"Hola \" + 5);\n" +
                      "bar = 10 / 2 * 5;\n" +
                      "print(foo + bar + ' - baz');\n";

        ASTNode ast = this.parser.parse(lexer.lex(line));

        System.out.println("\n\n### EXPECTING SUCCESS ###");
        interpreter.interpret(ast);
    }
}
