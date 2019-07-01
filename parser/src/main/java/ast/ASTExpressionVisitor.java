package ast;

import java.util.Map;

public interface ASTExpressionVisitor {
    String visit(Identifier identifier, Map<String, Object> programMemory);
    Object visit(BinaryExpression identifier, Map<String, Object> programMemory);
    Object visit(Literal identifier);
}
