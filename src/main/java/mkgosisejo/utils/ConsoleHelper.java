package mkgosisejo.utils;

public class ConsoleHelper {
    public static void InvalidInput(){
        SwingyIO.ConsoleOutLine(Messages.INVALID_INPUT);
        PressEnterToContinue();
    }

    public static void PressEnterToContinue(){
        SwingyIO.ConsoleOutLine(Messages.PRESS_ENTER_CONTINUE);
        SwingyIO.ConsoleInput();
    }

    public static void ExitSwingy(){
        System.exit(0);
    }
}