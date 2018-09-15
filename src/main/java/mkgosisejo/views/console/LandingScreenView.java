package mkgosisejo.views.console;

import mkgosisejo.utils.Messages;
import mkgosisejo.utils.SwingyIO;

public class LandingScreenView {
    private static boolean isFirst = true;

    public int SelectOrCreateHero(){
        String text = "";

        if (isFirst == true){
            text += "Welcome to Swingy...\n\n";
            isFirst = false;
        }
        text += "1. Create New Hero\n" +
            "2. Select Existing Hero\n" +
            "\n" +
            "3. " + Messages.SWITCH_TO_GUI + "\n" +
            "4. " + Messages.EXIT_SWINGY;

        SwingyIO.ConsoleOutLine(text);
        return (SwingyIO.ConsoleInputInt());
    }    
}