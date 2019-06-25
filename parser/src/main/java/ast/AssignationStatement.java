package ast;

import java.util.List;

public class AssignationStatement implements ASTNode {
    @Override
    public List<ASTNode> getChildren() {
        return null;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.accept(this);
    }
}
