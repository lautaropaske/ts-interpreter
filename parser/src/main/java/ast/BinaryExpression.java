package ast;

import java.util.Map;

public class BinaryExpression implements ASTExpression {
    private ASTExpression left;
    private BinaryOperation operation;
    private ASTExpression right;

    public BinaryExpression(ASTExpression left, BinaryOperation operation, ASTExpression right) {
        this.left = left;
        this.operation = operation;
        this.right = right;
    }

    public BinaryOperation getOperation() {
        return operation;
    }

    public ASTExpression getLeft() {
        return left;
    }

    public ASTExpression getRight() {
        return right;
    }

    @Override
    public Object accept(ASTExpressionVisitor visitor, Map<String, Object> programMemory) {
        return visitor.visit(this, programMemory);
    }
}
