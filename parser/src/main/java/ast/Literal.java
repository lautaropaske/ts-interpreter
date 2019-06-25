package ast;

import java.util.Collections;
import java.util.List;

public class Literal implements ASTNode{
    private Type type;
    private String value;

    public Literal(Type type, String value){
        this.type = type;
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

    public Type getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
