package interpreter;

import ast.ASTNode;

public interface Interpreter {
    void interpret(ASTNode program);
}
