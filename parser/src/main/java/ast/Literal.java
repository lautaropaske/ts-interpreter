package ast;


import java.util.Map;

public class Literal implements ASTExpression{
    private Type type;
    private String value;

    public Literal(Type type, String value){
        this.type = type;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public Object accept(ASTExpressionVisitor visitor, Map<String, Object> programMemory) {
        return visitor.visit(this);
    }
}
