package ast;

public interface ASTVisitor{
    void visit(PrintStatement printStatement);
    void visit(AssignationStatement assignationStatement);
    void visit(DeclarationStatement declarationStatement);
    void visit(DeclarationAssignationStatement declarationAssignationStatement);
    void visit(Program program);
}
