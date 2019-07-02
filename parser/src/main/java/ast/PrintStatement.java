package ast;

public class PrintStatement implements ASTNode {
    private ASTExpression expression;

    public PrintStatement(ASTExpression expression) {
        this.expression = expression;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    public ASTExpression getExpression(){ return expression;}
}
