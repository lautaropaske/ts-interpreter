package interpreter;

import ast.*;
import interpreter.exceptions.InterpreterException;

import java.util.HashMap;
import java.util.Map;

public class ASTVisitorImpl implements ASTVisitor {
    private Map<String, Object> programMemory;
    private ASTExpressionVisitor expVisitor;

    ASTVisitorImpl(){
        this.programMemory = new HashMap<>();
        this.expVisitor = new ExpressionVisitor();
    }

    @Override
    public void visit(PrintStatement printStatement) {
        Object result = printStatement.getPrintable().accept(expVisitor, programMemory);
        System.out.println(result);
    }

    @Override
    public void visit(AssignationStatement assignationStatement) {
        String identifier = assignationStatement.getIdentifier().getValue();

        if(programMemory.containsKey(identifier)) programMemory.put(identifier, assignationStatement.getExpression().accept(expVisitor, programMemory));

        throw new InterpreterException("\'" + identifier + "\' is unknown");
    }

    @Override
    public void visit(DeclarationStatement declarationStatement) { programMemory.put(declarationStatement.getIdentifier().getValue(), null); }

    @Override
    public void visit(DeclarationAssignationStatement adStatement) {
        Object expressionResult = adStatement.getExpression().accept(expVisitor, programMemory);
        Type type = adStatement.getType();
        Identifier identifier = adStatement.getIdentifier();

        if(isCorrectType(type, expressionResult)) { programMemory.put(identifier.getValue(), expressionResult); return; }
        throw new InterpreterException("Invalid type: " + expressionResult + "is not a " + type.name());
    }

    private boolean isCorrectType(Type type, Object expressionResult) {
        return false; // TODO
    }

    @Override
    public void visit(Program program) {
        for (ASTNode child : program.getStatements()) {
            child.accept(this);
        }
    }
}
