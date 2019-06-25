package ast;

public interface ASTVisitor {
    void accept(Literal literal);
    void accept(Identifier identifier);
    void accept(PrintStatement printStatement);
    void accept(BinaryExpression binaryExpression);
    void accept(AssignationStatement assignationStatement);
    void accept(DeclarationStatement declarationStatement);
    void accept(DeclarationAssignationStatement assignationDeclarationStatement);
    void accept(Program program);
}
