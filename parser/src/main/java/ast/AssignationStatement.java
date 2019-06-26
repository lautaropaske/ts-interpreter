package ast;

import java.util.Arrays;
import java.util.List;

public class AssignationStatement implements ASTNode {

    private ASTNode identifier;
    private ASTNode value;

    public AssignationStatement(ASTNode identifier, ASTNode value) {
        this.identifier = identifier;
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

    public ASTNode getIdentifier() {
        return identifier;
    }

    public ASTNode getValue() {
        return value;
    }
}
