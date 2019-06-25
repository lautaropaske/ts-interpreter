package tokens;

public interface Token {
    TokenType getType();
    int[] getCoordinates();
    String getValue();
}
