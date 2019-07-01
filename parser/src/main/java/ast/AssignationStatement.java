package ast;

public class AssignationStatement implements ASTNode {

    private Identifier identifier;
    private ASTExpression expression;

    public AssignationStatement(Identifier identifier, Literal literal) {
        this.identifier = identifier;
        this.expression = literal;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public ASTExpression getExpression() { return expression;
    }
}
