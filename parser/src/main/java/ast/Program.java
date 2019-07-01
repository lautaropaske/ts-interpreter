package ast;

import java.util.ArrayList;
import java.util.List;

public class Program implements ASTNode {

    private List<ASTNode> statements;

    public Program() {
        this.statements = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    public void addStatement(ASTNode statement){
        this.statements.add(statement);
    }

    public List<ASTNode> getStatements() { return statements; }
}
