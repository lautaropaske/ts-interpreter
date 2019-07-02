package handlers;

import ast.*;
import exceptions.ParserException;
import tokens.Token;
import tokens.TokenType;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ExpressionHandler extends Handler<ASTExpression> {

    @Override
    protected ASTExpression parseNode(List<Token> expression) {
        return parseBinaryExpression(TokenType.SUM, expression).orElseGet(
                () -> parseBinaryExpression(TokenType.MINUS, expression).orElseGet(
                        () -> parseBinaryExpression(TokenType.MULTIPLY, expression).orElseGet(
                                () -> parseBinaryExpression(TokenType.DIVIDE, expression).orElseGet(
                                        ()-> parseIdentifierOrLiteral(expression)
                               )
                       )
                )
        );
    }

    private ASTExpression parseIdentifierOrLiteral(List<Token> expression) {
        Token token = expression.get(0);

        if(TokenType.NUM_LITERAL == token.getType()) return new Literal(Type.number, token.getValue());
        if(TokenType.STR_LITERAL == token.getType()) return new Literal(Type.string, token.getValue());
        if(TokenType.IDENTIFIER == token.getType()) return new Identifier(token.getValue());

        throw new ParserException("Invalid expression", token.getCoordinates()[0], token.getCoordinates()[1]);
    }

    private Optional<ASTExpression> parseBinaryExpression(TokenType binOp, List<Token> expression) {
        int indexOfBinOp = expression.stream()
                                     .map(Token::getType)
                                     .collect(Collectors.toList())
                                     .indexOf(binOp);

        if(indexOfBinOp != -1){
            List<Token> left = expression.subList(0, indexOfBinOp);
            List<Token> right = expression.subList(indexOfBinOp+1, expression.size());
            ASTExpression binEx = new BinaryExpression(parseNode(left), BinaryOperation.from(binOp), parseNode(right));
            return Optional.of(binEx);
        } else return Optional.empty();
    }
}
