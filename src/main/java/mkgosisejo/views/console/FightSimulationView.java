package mkgosisejo.views.console;

import mkgosisejo.utils.SwingyIO;

public class FightSimulationView {
    public int pickUpArtifact(String message){
        SwingyIO.ConsoleOutLine(message);
        SwingyIO.ConsoleOutLine("1. Pick Up Artifact");
        SwingyIO.ConsoleOutLine("2. Leave Artifact");
        return (SwingyIO.ConsoleInputInt());
    }    
}