package mkgosisejo.views.console;

import mkgosisejo.enums.Moves;
import mkgosisejo.models.GameModel;
import mkgosisejo.utils.SwingyIO;

public class GameView {
    public int getMove(){
        SwingyIO.ConsoleOutLine("Move by selecting the following (use numbers):");
        for (int i = 0; i < Moves.values().length; i++) {
            SwingyIO.ConsoleOutLine((i + 1) + ". " + Moves.values()[i].toString());
        }
        return (SwingyIO.ConsoleInputInt());
    }

    public int getFightOrRun(String enemyStats){
        SwingyIO.ConsoleOutLine(GameModel.YOU_HAVE_ENCOUNTERED_AN_ENEMY + ":");
        SwingyIO.ConsoleOut(enemyStats);
        SwingyIO.ConsoleOutLine();
        SwingyIO.ConsoleOutLine();
        SwingyIO.ConsoleOutLine(GameModel.FIGHT_OR_RUN);
        SwingyIO.ConsoleOutLine("1. Yes");
        SwingyIO.ConsoleOutLine("2. No");
        return (SwingyIO.ConsoleInputInt());
    }
}