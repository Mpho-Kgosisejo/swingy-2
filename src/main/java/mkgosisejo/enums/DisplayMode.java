package mkgosisejo.enums;

public enum DisplayMode {
    CONSOLE,
    GUI;

    public static DisplayMode getEnumValue(String value){
        try {
            return (DisplayMode.valueOf(value.toUpperCase()));
        } catch (Exception e) {}
        return null;
    }
}