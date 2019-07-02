package ast;


import exceptions.ParserException;
import tokens.Token;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Type {
    number,
    string;

    /**
     * Takes a type declaration token and matches it's correspondent Type enum
     *
     * @param token type declaration token such as 'number' or 'string'
     * @return a Type enum value
     */
    public static Type from(Token token) {
        List<Type> list = Stream.of(Type.values())
                                .filter(v -> v.name().equals(token.getValue()))
                                .collect(Collectors.toList());
        if(!list.isEmpty()) return list.get(0);
        throw new ParserException("\'" + token.getValue()+ "\' doesn't match a Parser enum Type", token.getCoordinates()[0], token.getCoordinates()[1]);
    }

    public static boolean isValid(String value) {
        return Stream.of(Type.values())
                     .map(Type::name)
                     .collect(Collectors.toList())
                     .contains(value);
    }
}
