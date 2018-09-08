package mkgosisejo;

import mkgosisejo.config.Config;
import mkgosisejo.dataproviders.Cache;
import mkgosisejo.enums.DisplayMode;
import mkgosisejo.utils.FileHandler;
import mkgosisejo.utils.SwingyIO;

public class App 
{
    public static void main( String[] args )
    {
        new Config(args);

        if (Cache.Args.DISPLAY_MODE == DisplayMode.CONSOLE){
            SwingyIO.ConsoleOutLine("Console");
        }
        else if (Cache.Args.DISPLAY_MODE == DisplayMode.GUI){
            SwingyIO.GUIOut("GUI");
        }
    }
}
