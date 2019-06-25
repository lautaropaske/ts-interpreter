package ast;

import java.util.Arrays;
import java.util.List;

public class PrintStatement implements ASTNode {
    private ASTNode printable;

    public PrintStatement(ASTNode printable) {
        this.printable = printable;
    }

    @Override
    public List<ASTNode> getChildren() {
        return Arrays.asList(printable);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.accept(this);
    }
}
