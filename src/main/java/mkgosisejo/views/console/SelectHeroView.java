package mkgosisejo.views.console;

import java.util.List;

import mkgosisejo.models.Hero;
import mkgosisejo.utils.ConsoleHelper;
import mkgosisejo.utils.Messages;
import mkgosisejo.utils.SwingyIO;

public class SelectHeroView {
    public static String STR_DELETION = "Deletion";
    public static String STR_DELETING = "Deleting";
    public static String STR_UPDATE = "Update";
    public static String STR_UPDATING = "Updating";

    public void showNoHerosMssg(){
        SwingyIO.ConsoleOutLine();
        SwingyIO.ConsoleOutLine("There's no existing heros to select. Try creating a new Hero.");
        ConsoleHelper.PressEnterToContinue();
    }

    public int showHeros(List<Hero> heros){
        int i = 1;

        SwingyIO.ConsoleOutLine();
        SwingyIO.ConsoleOutLine(String.format("   %10.10s | %6.6s | %s", "HERO NAME", "TYPE", "LEVEL"));
        for (Hero hero: heros) {
            SwingyIO.ConsoleOutLine(String.format("%d. %10.10s | %6.6s | %5d", i, hero.getName(), hero.getType(), hero.getLevel()));
            i++;
        }
        SwingyIO.ConsoleOutLine();
        SwingyIO.ConsoleOutLine(i++ + ". " + Messages.SWITCH_TO_GUI);
        SwingyIO.ConsoleOutLine(i++ + ". " + Messages.EXIT_SWINGY);
        return (SwingyIO.ConsoleInputInt());
    }

    public int showHeroDetails(Hero hero){
        SwingyIO.ConsoleOutLine();
        SwingyIO.ConsoleOutLine("Selected Hero Details:");
        SwingyIO.ConsoleOutLine(String.format(" %9.9s | %s", "Name", hero.getName()));
        SwingyIO.ConsoleOutLine(String.format(" %9.9s | %s", "Type", hero.getType()));
        SwingyIO.ConsoleOutLine(String.format(" %9.9s | %s", "Level", (hero.getLevel() + "(" + hero.getXp() + "XP)")));
        SwingyIO.ConsoleOutLine(String.format(" %9.9s | %s", "Artifact", hero.getArtifact()));
        SwingyIO.ConsoleOutLine(String.format(" %9.9s | %s", "Attack", hero.getAttack()));
        SwingyIO.ConsoleOutLine(String.format(" %9.9s | %s", "Defence", hero.getDefence()));
        SwingyIO.ConsoleOutLine(String.format(" %9.9s | %s", "Hit Point", (hero.getHp() + "HP")));

        SwingyIO.ConsoleOutLine();
        SwingyIO.ConsoleOutLine("1. Start Game (with Selected Hero)");
        SwingyIO.ConsoleOutLine("2. Go Back (to Hero Selection)");
        SwingyIO.ConsoleOutLine();
        SwingyIO.ConsoleOutLine("3. Update Hero");
        SwingyIO.ConsoleOutLine("4. Delete Hero");
        return (SwingyIO.ConsoleInputInt());
    }

    public void showErrorMssg(String type){
        SwingyIO.ConsoleOutLine(Messages.SOMETHING_WENT_WRONG + " " + type + " Hero");
        ConsoleHelper.PressEnterToContinue();
    }

    public void showSuccessMssg(String type){
        SwingyIO.ConsoleOutLine(type + " was successful");
        ConsoleHelper.PressEnterToContinue();
    }
}