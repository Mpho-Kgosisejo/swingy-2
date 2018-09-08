package mkgosisejo.enums;

public enum DataProviderType {
    FILE,
    DATABASE;

    public static DataProviderType getEnumValue(String value){
        try {
            return (DataProviderType.valueOf(value.toUpperCase()));
        } catch (Exception e) {}
        return null;
    }
}