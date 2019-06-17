package tokens;

import java.util.Optional;

public interface Token {
    TokenType getType();
    int[] getCoordinates();
    Optional<String> getValue();
}
