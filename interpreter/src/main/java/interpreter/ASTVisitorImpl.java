package interpreter;

import ast.*;
import interpreter.exceptions.InterpreterException;
import utils.Definitions;

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
        Object result = printStatement.getExpression().accept(expVisitor, programMemory);
        System.out.println(
                result.toString()
                      .replace("\'", "")
                      .replace("\"", "")
        );
    }

    @Override
    public void visit(AssignationStatement assignationStatement) {
        String identifier = assignationStatement.getIdentifier().getValue();

        if(programMemory.containsKey(identifier)) {
            programMemory.put(identifier, assignationStatement.getExpression().accept(expVisitor, programMemory));
        } else throw new InterpreterException("\'" + identifier + "\' is unknown");
    }

    @Override
    public void visit(DeclarationStatement declarationStatement) { programMemory.put(declarationStatement.getIdentifier().getValue(), null); }

    @Override
    public void visit(DeclarationAssignationStatement adStatement) {
        Object expressionResult = adStatement.getExpression().accept(expVisitor, programMemory);
        Type type = adStatement.getType();
        Identifier identifier = adStatement.getIdentifier();

        boolean bool = isCorrectType(type, expressionResult);
        if(bool) { programMemory.put(identifier.getValue(), expressionResult); return; }
        throw new InterpreterException("Invalid type: " + expressionResult + " is not a " + type.name());
    }

    private boolean isCorrectType(Type type, Object expressionResult) {
        if(type.name().equals("number")) {
            String rs = expressionResult.toString();
            if(rs.matches(Definitions.NUM_LITERAL_REGEX)) return true;
        }

        return type.name().equals("string") && expressionResult.toString().matches(Definitions.STR_LITERAL_REGEX);
    }

    @Override
    public void visit(Program program) { for (ASTNode child : program.getStatements()) child.accept(this); }
}
