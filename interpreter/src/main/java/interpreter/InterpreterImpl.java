package interpreter;

import ast.ASTNode;
import ast.ASTVisitor;

public class InterpreterImpl implements Interpreter {
    private ASTVisitor visitor = new ASTVisitorImpl();

    @Override
    public void interpret(ASTNode program) {
        program.accept(visitor);
    }
}
