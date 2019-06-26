package ast;

import tokens.TokenType;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum BinaryOperation {
    SUM,
    MULTIPLY,
    DIVIDE,
    MINUS;

    public static BinaryOperation from(TokenType binOp) {
        return Stream.of(BinaryOperation.values())
                     .filter(v -> v.name().equals(binOp.name()))
                     .collect(Collectors.toList())
                     .get(0);
    }
}
