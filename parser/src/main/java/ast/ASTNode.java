package ast;

import java.util.List;

public interface ASTNode {
    List<ASTNode> getChildren();
    void accept(ASTVisitor visitor);
}
