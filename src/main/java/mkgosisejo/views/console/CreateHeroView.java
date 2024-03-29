package mkgosisejo.views.console;

import mkgosisejo.enums.Artifacts;
import mkgosisejo.enums.HeroTypes;
import mkgosisejo.utils.Messages;
import mkgosisejo.utils.SwingyIO;

public class CreateHeroView {
    public String getName(){
        SwingyIO.ConsoleOutLine(Messages.ENTER_HERO_NAME);
        return (SwingyIO.ConsoleInput());
    }

    public int getType(){
        SwingyIO.ConsoleOutLine("Select Hero TYPE:");
        for (int i = 0; i < HeroTypes.values().length; i++) {
            SwingyIO.ConsoleOutLine((i + 1) + ". " + HeroTypes.values()[i]);
        }
        return(SwingyIO.ConsoleInputInt());
    }

    public int getArtifact(){
        SwingyIO.ConsoleOutLine("Select Hero ARTIFACT:");
        for (int i = 0; i < Artifacts.values().length; i++) {
            SwingyIO.ConsoleOutLine((i + 1) + ". " + Artifacts.values()[i]);
        }
        return (SwingyIO.ConsoleInputInt());
    }
}