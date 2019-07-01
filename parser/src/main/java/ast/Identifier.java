package ast;

import java.util.Map;

public class Identifier implements ASTExpression {
    private String value;

    public Identifier(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public Object accept(ASTExpressionVisitor visitor, Map<String, Object> programMemory) {
        return null;
    }
}
