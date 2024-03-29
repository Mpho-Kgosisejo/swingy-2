package mkgosisejo.utils;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class SwingyIO {
    public static void ConsoleOut(String message) {
        System.out.print(message);
    }

    public static void ConsoleOutLine(){
        ConsoleOutLine("");
    }

    public static void ConsoleOutLine(String message) {
        ConsoleOut(message + "\n");
    }

    public static void ConsoleClear(){
        final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        ConsoleOut(ANSI_CLS + ANSI_HOME);
        System.out.flush();
    }

    public static String ConsoleInput(){
        Scanner scanner = new Scanner(System.in);
        String input = null;
        
        SwingyIO.ConsoleOut("$> ");
        input = scanner.nextLine();
        // scanner.close();
        return (input);
    }

    public static int ConsoleInputInt(){
        int num = -1;

        try {
            num = Integer.parseInt(ConsoleInput());
        } catch (Exception e) {}
        return num;
    }

    public static void GUIOut(String message){
        JOptionPane.showMessageDialog(null, message);
    }

    public static boolean GUIConfirm(String message){
        return (JOptionPane.showConfirmDialog(null, message, "Confirm...?", JOptionPane.YES_NO_OPTION) == 0);
    }
}