package mkgosisejo.controllers;

import mkgosisejo.controllers.console.CreateHeroController;
import mkgosisejo.controllers.console.LandingScreenController;
import mkgosisejo.controllers.console.SelectHeroController;
import mkgosisejo.enums.DisplayMode;
import mkgosisejo.models.CreateHeroModel;
import mkgosisejo.models.SelectHeroModel;
import mkgosisejo.providers.cache.Cache;
import mkgosisejo.utils.SwingyIO;
import mkgosisejo.views.console.CreateHeroView;
import mkgosisejo.views.console.LandingScreenView;
import mkgosisejo.views.console.SelectHeroView;

public class AppController {
    public static void LandingScreen(){
        if (Cache.Args.DISPLAY_MODE == DisplayMode.GUI){
            SwingyIO.GUIOut("GUI");
        }
        else{
            new LandingScreenController(new LandingScreenView());
        }
    }    

    public static void SelectHero(){
        if (Cache.Args.DISPLAY_MODE == DisplayMode.GUI){
            SwingyIO.GUIOut("GUI().SelectHero()");
        }
        else{
            new SelectHeroController(new SelectHeroView(), new SelectHeroModel());
        }
    }

    public static void CreateHero(){
        if (Cache.Args.DISPLAY_MODE == DisplayMode.GUI){
            SwingyIO.ConsoleOutLine("GUI().CreateHero()");
        }
        else{
            new CreateHeroController(new CreateHeroView(), new CreateHeroModel());
        }
    }
}