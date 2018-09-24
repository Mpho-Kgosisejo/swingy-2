package mkgosisejo.enums;

public enum Moves {
    UP,
    RIGHT,
    DOWN,
    LEFT;

    public static Moves getEnumValue(String value){
        try {
            return (Moves.valueOf(value.toUpperCase()));
        } catch (Exception e) {}
        return null;
    }
}