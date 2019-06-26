package ast;


import tokens.Token;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Type {
    number,
    string;

    public static Type from(Token token) {
        return Stream.of(Type.values())
                     .filter(v -> v.name().equals(token.getValue()))
                     .collect(Collectors.toList())
                     .get(0);
    }

    public static boolean isValid(String value) {
        return Stream.of(Type.values())
                .map(Type::name)
                .collect(Collectors.toList())
                .contains(value);
    }
}
