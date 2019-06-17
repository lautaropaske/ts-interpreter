package tokens;

import java.util.Objects;
import java.util.Optional;

public class TokenImpl implements Token{
    private TokenType type;
    private int[] coordinates;
    private Optional<String> value;

    public TokenImpl(TokenType type, int[] coordinates , String value){
        Objects.requireNonNull(type);
        Objects.requireNonNull(coordinates);

        this.type = type;
        this.coordinates = coordinates;
        if (Objects.isNull(value)) {
            this.value = Optional.empty();
        } else {
            this.value = Optional.of(value);
        }
    }


    @Override
    public TokenType getType() {
        return type;
    }

    @Override
    public int[] getCoordinates() {
        return coordinates;
    }

    @Override
    public Optional<String> getValue() {
        return value;
    }
}
