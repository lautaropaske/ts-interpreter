package handlers.rules;

import tokens.Token;

import java.util.List;

public class DeclarationAndAssignationRule implements Rule{
    DeclarationRule declarationRule;
    AssignationRule assignationRule;

    @Override
    public void validate(List<Token> statement) {
        declarationRule.validate(statement.subList(0,4));
        assignationRule.validate(statement.subList(4, statement.size()));
    }
}
