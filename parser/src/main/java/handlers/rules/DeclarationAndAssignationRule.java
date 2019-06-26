package handlers.rules;

import tokens.Token;

import java.util.List;

public class DeclarationAndAssignationRule implements Rule{
    private DeclarationRule declarationRule = new DeclarationRule();
    private AssignationRule assignationRule = new AssignationRule();

    @Override
    public void validate(List<Token> statement) {
        // TODO Revisar,  está mal porque el assignation no recibe identifier
        declarationRule.validate(statement.subList(0,4));
        assignationRule.validate(statement.subList(4, statement.size()));
    }
}
