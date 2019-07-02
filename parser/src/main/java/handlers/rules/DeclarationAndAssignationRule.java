package handlers.rules;

import tokens.Token;
import java.util.List;

public class DeclarationAndAssignationRule implements Rule{

    @Override
    public void validate(List<Token> statement) {
        // Declaration
        isDeclaration(statement.subList(0,5), true);

        // Assignation
        matchingParens(statement.subList(5, statement.size()));
        isExpression(statement.subList(5, statement.size()));
    }
}
