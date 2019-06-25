package tokens;

public class TokenImpl implements Token{
    private TokenType type;
    private int[] coordinates;
    private String value;

    public TokenImpl(TokenType type, int[] coordinates , String value){
        this.type = type;
        this.coordinates = coordinates;
        this.value = value;
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
    public String getValue() {
        return value;
    }
}
