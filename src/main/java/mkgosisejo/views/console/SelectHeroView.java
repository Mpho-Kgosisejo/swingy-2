package mkgosisejo.views.console;

import java.util.List;

import mkgosisejo.models.Hero;
import mkgosisejo.models.SelectHeroModel;
import mkgosisejo.utils.ConsoleHelper;
import mkgosisejo.utils.Messages;
import mkgosisejo.utils.SwingyIO;

public class SelectHeroView {
    public void showNoHerosMssg(){
        SwingyIO.ConsoleOutLine();
        SwingyIO.ConsoleOutLine(SelectHeroModel.NO_EXISTING_HEROS + ". " + "Try creating a new Hero.");
        ConsoleHelper.PressEnterToContinue();
    }

    public int showHeros(List<Hero> heros){
        int i = 1;

        SwingyIO.ConsoleOutLine();
        SwingyIO.ConsoleOutLine(String.format("   %10.10s | %6.6s | %s", "HERO NAME", "TYPE", "LEVEL(XP)"));
        for (Hero hero: heros) {
            SwingyIO.ConsoleOutLine(String.format("%d. %10.10s | %6.6s | %s", i, hero.getName(), hero.getType(), (hero.getLevel() + "(" + hero.getXp() + "XP)")));
            i++;
        }
        SwingyIO.ConsoleOutLine();
        SwingyIO.ConsoleOutLine(i++ + ". " + "Create New Hero");
        SwingyIO.ConsoleOutLine(i++ + ". " + Messages.SWITCH_TO_GUI);
        SwingyIO.ConsoleOutLine(i++ + ". " + Messages.EXIT_SWINGY);
        return (SwingyIO.ConsoleInputInt());
    }

    public int showHeroDetails(Hero hero){
        SwingyIO.ConsoleOutLine();
        SwingyIO.ConsoleOutLine("Selected Hero Details:");
        SwingyIO.ConsoleOutLine(SelectHeroModel.GetHeroDetails(hero));

        SwingyIO.ConsoleOutLine();
        SwingyIO.ConsoleOutLine("1. Start Game (with Selected Hero)");
        SwingyIO.ConsoleOutLine("2. Go Back (to Hero Selection)");
        SwingyIO.ConsoleOutLine();
        SwingyIO.ConsoleOutLine("3. Update Hero");
        SwingyIO.ConsoleOutLine("4. Delete Hero");
        return (SwingyIO.ConsoleInputInt());
    }

    public int showDeleteHero(){
        SwingyIO.ConsoleOutLine(Messages.WANT_TO_DELETE_HERO);
        SwingyIO.ConsoleOutLine("1. Yes");
        SwingyIO.ConsoleOutLine("2. No");
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