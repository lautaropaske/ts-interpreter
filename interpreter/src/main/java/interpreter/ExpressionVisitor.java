package interpreter;

import ast.*;
import com.sun.istack.internal.Nullable;
import interpreter.exceptions.InterpreterException;
import parser.Parser;
import utils.Definitions;

import java.util.Map;

public class ExpressionVisitor implements ASTExpressionVisitor {

    @Override
    public String visit(Identifier identifier, Map<String, Object> programMemory) {
        String val = identifier.getValue();
        if (val != null && programMemory.containsKey(val)) return val;
        throw new RuntimeException( "\'" + val + "\' is unknown");
    }

    @Override
    public Object visit(BinaryExpression expression, Map<String, Object> programMemory) {
        BinaryOperation operator = expression.getOperation();
        String leftResult = expression.getRight().accept(this, programMemory).toString();
        String rightResult = expression.getLeft().accept(this, programMemory).toString();

        return solve(leftResult, operator, rightResult);
    }

    private Object solve(String left, BinaryOperation operator, String right) {
        if (left.matches(Definitions.STR_LITERAL_REGEX) || right.matches(Definitions.STR_LITERAL_REGEX)) return solveStrings(left, operator, right);
        return solveNumbers(Long.parseLong(left), operator, Long.parseLong(right));
    }

    private Object solveStrings(String left, BinaryOperation operator, String right) {
        if (operator == BinaryOperation.SUM) return left + right;
        throw new InterpreterException("Invalid string operation");
    }

    private Object solveNumbers(Long left, BinaryOperation operator, Long right) {
        switch (operator) {
            case SUM: return left + right;
            case MINUS: return left - right;
            case MULTIPLY: return left * right;
            case DIVIDE: return left / right;
            default: throw new InterpreterException("Invalid number operation");
        }
    }

    @Override
    public Object visit(Literal literal) {
        return literal.getValue();
    }
}
