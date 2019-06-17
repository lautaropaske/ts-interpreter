package utils;

import java.util.Arrays;
import java.util.List;

/**
 * Immutable global definitions
 */
public class Utils {

    public static final List<String> KEYWORD_LIST = Arrays.asList("print", "let");
    public static final List<String> SYMBOL_LIST = Arrays.asList("=", "+", "-", "*", "/", "(", ")", ";", ":");
    public static final List<String> DELIMITER_LIST = Arrays.asList(" ", "\n");

    public static final String STR_LITERAL_REGEX = "\'.*\'";
    public static final String NUM_LITERAL_REGEX = "[0-9]+";
    public static final String IDENTIFIER_REGEX = "[aA-zZ]+[0-9]*[aA-zZ]*";
}
