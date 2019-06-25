import ast.ASTNode;
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
    public void tokenizeAnAssignation() {
        String line = "let foo : number = 5;\n" +
                      "print(foo + 3);";

        ASTNode ast = this.parser.parse(lexer.tokenize(line));

        ASTNode expectedAst = generateASTForTest(lexer.tokenize(line));

        assertTreeEquality(expectedAst, ast);
    }

    private ASTNode generateASTForTest(List<Token> tokenize) {
        return null;
    }

    private void assertTreeEquality(ASTNode expectedAst, ASTNode ast){

    }
}
