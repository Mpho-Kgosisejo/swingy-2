package mkgosisejo;

import java.util.List;

import mkgosisejo.config.Config;
import mkgosisejo.models.Hero;
import mkgosisejo.providers.DataProvider;
import mkgosisejo.utils.SwingyIO;

public class App 
{

    public static void main( String[] args )
    {
        new Config(args);

        // if (Cache.Args.DISPLAY_MODE == DisplayMode.CONSOLE){
        //     SwingyIO.ConsoleOutLine("Console");
        // }
        // else if (Cache.Args.DISPLAY_MODE == DisplayMode.GUI){
        //     SwingyIO.GUIOut("GUI");
        // }

        List<Hero> heros = new DataProvider().getHeros();
        SwingyIO.ConsoleOutLine(">> " + heros.size());
    }
}
