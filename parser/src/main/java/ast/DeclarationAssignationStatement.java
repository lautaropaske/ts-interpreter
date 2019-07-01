package ast;

public class DeclarationAssignationStatement implements ASTNode {

    private Identifier identifier;
    private Type type;
    private ASTExpression expression;

    public DeclarationAssignationStatement(Identifier identifier, Type type, ASTExpression expression) {
        this.identifier = identifier;
        this.type = type;
        this.expression = expression;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public Type getType() {
        return type;
    }

    public ASTExpression getExpression() {
        return expression;
    }
}
