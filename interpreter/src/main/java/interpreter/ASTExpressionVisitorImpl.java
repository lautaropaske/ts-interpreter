package interpreter;

import ast.*;
import interpreter.exceptions.InterpreterException;
import utils.Definitions;

import java.util.Map;

public class ASTExpressionVisitorImpl implements ASTExpressionVisitor {

    @Override
    public Object visit(Literal literal) {
        return literal.getValue();
    }

    @Override
    public String visit(Identifier identifier, Map<String, Object> programMemory) {
        String val = identifier.getValue();
        if (val != null && programMemory.containsKey(val)) return programMemory.get(val).toString();
        throw new RuntimeException( "\'" + val + "\' is unknown");
    }

    @Override
    public Object visit(BinaryExpression expression, Map<String, Object> programMemory) {
        BinaryOperation operator = expression.getOperation();
        String leftResult = expression.getLeft().accept(this, programMemory).toString();
        String rightResult = expression.getRight().accept(this, programMemory).toString();

        return solve(leftResult, operator, rightResult);
    }

    private Object solve(String left, BinaryOperation operator, String right) {
        if (left.matches(Definitions.STR_LITERAL_REGEX) || right.matches(Definitions.STR_LITERAL_REGEX)) return solveStrings(left, operator, right);
        return solveNumbers(Float.parseFloat(left), operator, Float.parseFloat(right));
    }

    private Object solveStrings(String left, BinaryOperation operator, String right) {
        if (operator == BinaryOperation.SUM) return left + right;
        throw new InterpreterException("Invalid string operation");
    }

    private Object solveNumbers(Float left, BinaryOperation operator, Float right) {
        switch (operator) {
            case SUM: return left + right;
            case MINUS: return left - right;
            case MULTIPLY: return left * right;
            case DIVIDE: return left / right;
            default: throw new InterpreterException("Invalid number operation");
        }
    }
}
