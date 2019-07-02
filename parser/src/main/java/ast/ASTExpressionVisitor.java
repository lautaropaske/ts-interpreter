package ast;

import java.util.Map;

public interface ASTExpressionVisitor {
    String visit(Identifier identifier, Map<String, Object> programMemory);
    Object visit(BinaryExpression binaryExpression, Map<String, Object> programMemory);
    Object visit(Literal literal);
}
