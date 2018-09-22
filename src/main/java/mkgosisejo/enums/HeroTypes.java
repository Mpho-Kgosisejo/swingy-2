package mkgosisejo.enums;

import mkgosisejo.utils.SwingyIO;

public enum HeroTypes {
    KING,
    KNIGHT,
    PAWN,
    PISHOP,
    QUEEN,
    ROOK;

    public static int getValueOfIndex(String enumStr){
        try {
            for (int i = 0; i < values().length; i++) {
                if (values()[i].toString().equalsIgnoreCase(enumStr.trim()))
                    return (i);
            }
        } catch (Exception e) {}
        return (-1);
    }
}