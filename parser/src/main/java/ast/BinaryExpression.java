package ast;

import java.util.Arrays;
import java.util.List;

public class BinaryExpression implements ASTNode {
    private ASTNode left;
    private BinaryOperation operation;
    private ASTNode right;

    public BinaryExpression(ASTNode left, BinaryOperation operation, ASTNode right) {
        this.left = left;
        this.operation = operation;
        this.right = right;
    }

    @Override
    public List<ASTNode> getChildren() {
        return Arrays.asList(left, right);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.accept(this);
    }

    public ASTNode getLeft() {
        return left;
    }

    public BinaryOperation getOperation() {
        return operation;
    }

    public ASTNode getRight() {
        return right;
    }
}
