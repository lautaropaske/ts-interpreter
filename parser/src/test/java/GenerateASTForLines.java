import ast.ASTNode;
import exceptions.ParserException;
import lexer.Lexer;
import lexer.LexerImpl;
import org.junit.jupiter.api.Test;
import parser.Parser;
import parser.ParserImpl;
import tokens.Token;

import java.util.List;

public class GenerateASTForLines {
    private Lexer lexer = new LexerImpl();
    private Parser parser = new ParserImpl();

    @Test
    public void pass_parseADeclarationAssignationAndPrint() {
        String line = "let foo : number = 5;\n" +
                      "print(foo + 3);";

        ASTNode ast = this.parser.parse(lexer.lex(line));

        System.out.println(ast);

        String line2 = "let foo : number = 'invalid assignation';\n" +
                "print(foo + 3);";

        ASTNode ast2 = this.parser.parse(lexer.lex(line2));

        System.out.println(ast2);
    }

    @Test
    public void pass_parseADeclarationAndAnAssignation() {
        String line = "let foo : string;\n" +
                      "foo = 'works';";

        ASTNode ast = this.parser.parse(lexer.lex(line));

        System.out.println(ast);
    }

    @Test
    public void fail_parseADeclarationAndAnAssignation() {
        String line = "let foo;\n" +
                      "foo = 'works';";

        ASTNode ast = null;
        try {
            ast = this.parser.parse(lexer.lex(line));
        } catch (ParserException ex) {
            System.out.println(ast);
            ex.printStackTrace();
        }


    }

    @Test
    public void fail_parseInvalidDeclaration() {
        String line = "let foo; = super invalid;";

        ASTNode ast = null;
        try {
            ast = this.parser.parse(lexer.lex(line));
        } catch (ParserException ex) {
            System.out.println(ast);
            ex.printStackTrace();
        }

        String line2 = "let foo = super invalid stuff;";

        ASTNode ast2 = null;
        try {
            ast2 = this.parser.parse(lexer.lex(line2));
        } catch (ParserException ex) {
            System.out.println(ast2);
            ex.printStackTrace();
        }

        String line3 = "let foo : number invalid stuff = 2;";

        ASTNode ast3 = null;
        try {
            ast3 = this.parser.parse(lexer.lex(line3));
        } catch (ParserException ex) {
            System.out.println(ast3);
            ex.printStackTrace();
        }
    }
}
