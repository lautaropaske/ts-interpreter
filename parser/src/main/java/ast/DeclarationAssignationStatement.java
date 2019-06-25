package ast;

import java.util.Arrays;
import java.util.List;

public class DeclarationAssignationStatement implements ASTNode {

    private ASTNode identifier;
    private Type type;
    private ASTNode value;

    public DeclarationAssignationStatement(ASTNode identifier, Type type, ASTNode value) {
        this.identifier = identifier;
        this.type = type;
        this.value = value;
    }

    @Override
    public List<ASTNode> getChildren() {
        return Arrays.asList(identifier, value);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.accept(this);
    }
}
