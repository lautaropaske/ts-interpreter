package ast;

import java.util.List;

public class Program implements ASTNode {

    private List<ASTNode> statements;

    @Override
    public List<ASTNode> getChildren() {
        return statements;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.accept(this);
    }

    public void addStatement(ASTNode statement){
        this.statements.add(statement);
    }
}
