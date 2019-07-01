package ast;

public class PrintStatement implements ASTNode {
    private ASTExpression printable;

    public PrintStatement(ASTExpression printable) {
        this.printable = printable;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    public ASTExpression getPrintable(){ return printable;}
}
