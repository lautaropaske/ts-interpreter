package ast;

import java.util.Map;

public interface ASTExpression {
    Object accept(ASTExpressionVisitor visitor, Map<String, Object> programMemory);
}
