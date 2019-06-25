package ast;

import java.util.Collections;
import java.util.List;

public class Identifier implements ASTNode {
    private String value;

    public Identifier(String value) {
        this.value = value;
    }


    @Override
    public List<ASTNode> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.accept(this);
    }

    public String getValue() {
        return value;
    }
}
