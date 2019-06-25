package ast;

import java.util.Arrays;
import java.util.List;

public class DeclarationStatement implements ASTNode {
    private ASTNode identifier;
    private Type type;

    public DeclarationStatement(ASTNode identifier, Type type) {
        this.identifier = identifier;
        this.type = type;
    }


    @Override
    public List<ASTNode> getChildren() {
        return Arrays.asList(identifier);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.accept(this);
    }

    public Type getType() {
        return type;
    }
}
