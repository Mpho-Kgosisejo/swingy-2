package mkgosisejo;

import mkgosisejo.config.ConfigApp;
import mkgosisejo.controllers.AppController;

public class App 
{
    public static void main( String[] args )
    {
        new ConfigApp(args);
        AppController.LandingScreen();
    }
}
