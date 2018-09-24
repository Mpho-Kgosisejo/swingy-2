package mkgosisejo.utils;

import java.util.Random;

public class Formulas {
    public static int MapSize(int level){
        return ((((level - 1) * 5) + 10) - (level % 2));
    }

    public static int NumberOfEnemiesToSpawn(int level){
        return ((level * 5) + 10);
    }

    public static int GetLevel(int xp) 
    {
        double level = 0;
        level = (-100 + Math.sqrt(-800000 + 1800 * ((double)xp))) / (900);
        return (int)level;   
    }

    public static int GetWinGameReward(int xp){
        xp = (((xp > 0) ? xp : 575) / 2);
        return (420 + (new Random().nextInt(xp)));
    }

    public static int GetWinFightReward(int xp){
        xp = (((xp > 0) ? xp : 42) / 4);
        return (42 + (new Random().nextInt(xp)));
    }
}