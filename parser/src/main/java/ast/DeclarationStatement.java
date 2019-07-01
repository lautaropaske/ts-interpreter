package ast;

public class DeclarationStatement implements ASTNode {
    private Identifier identifier;
    private Type type;

    public DeclarationStatement(Identifier identifier, Type type) {
        this.identifier = identifier;
        this.type = type;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    public Type getType() {
        return type;
    }

    public Identifier getIdentifier() {
        return identifier;
    }
}
