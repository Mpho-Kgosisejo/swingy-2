package mkgosisejo.enums;

public enum Artifacts{
    WEAPON,
    ARMOR,
    HELM;

    public static int getValueOfIndex(String enumStr){
        try {
            for (int i = 0; i < values().length; i++) {
                if (values()[i].toString().equalsIgnoreCase(enumStr))
                    return (i);
            }
        } catch (Exception e) {}
        return (-1);
    }
}