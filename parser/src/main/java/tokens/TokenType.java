package tokens;

public enum TokenType {
    // First, keywords

    PRINT,
    LET,
    NUM_TYPE,
    STR_TYPE,

    // Then, symbols

    ASSIGNATION,
    SUM,
    MINUS,
    MULTIPLY,
    DIVIDE,
    L_PAREN,
    R_PAREN,
    SEMICOLON,

    // Then, else
    IDENTIFIER,
    NUM_LITERAL,
    STR_LITERAL,
    UNKNOWN,
}
