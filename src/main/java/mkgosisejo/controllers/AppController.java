package mkgosisejo.controllers;

import mkgosisejo.controllers.console.CreateHeroController;
import mkgosisejo.controllers.console.FightSimulationController;
import mkgosisejo.controllers.console.GameController;
import mkgosisejo.controllers.console.LandingScreenController;
import mkgosisejo.controllers.console.SelectHeroController;
import mkgosisejo.controllers.gui.CreateHeroGUIController;
import mkgosisejo.controllers.gui.GameGUIController;
import mkgosisejo.controllers.gui.LandingScreenGUIController;
import mkgosisejo.controllers.gui.SelectHeroGUIController;
import mkgosisejo.enums.DisplayMode;
import mkgosisejo.models.CreateHeroModel;
import mkgosisejo.models.FightSimulationModel;
import mkgosisejo.models.GameModel;
import mkgosisejo.models.Hero;
import mkgosisejo.models.HeroEnemy;
import mkgosisejo.models.SelectHeroModel;
import mkgosisejo.providers.cache.Cache;
import mkgosisejo.utils.SwingyIO;
import mkgosisejo.views.console.CreateHeroView;
import mkgosisejo.views.console.FightSimulationView;
import mkgosisejo.views.console.GameView;
import mkgosisejo.views.console.LandingScreenView;
import mkgosisejo.views.console.SelectHeroView;
import mkgosisejo.views.gui.CreateHeroGUIView;
import mkgosisejo.views.gui.GameGUIView;
import mkgosisejo.views.gui.LandingScreenGUIView;
import mkgosisejo.views.gui.SelectHeroGUIView;

public class AppController {
    public static void LandingScreen(){
        if (Cache.Args.DISPLAY_MODE == DisplayMode.GUI){
            new LandingScreenGUIController(new LandingScreenGUIView());
        }else{
            new LandingScreenController(new LandingScreenView());
        }
    }    

    public static void SelectHero(){
        if (Cache.Args.DISPLAY_MODE == DisplayMode.GUI){
            new SelectHeroGUIController(new SelectHeroGUIView(), new SelectHeroModel());
        }else{
            new SelectHeroController(new SelectHeroView(), new SelectHeroModel());
        }
    }

    public static void CreateHero(){
        if (Cache.Args.DISPLAY_MODE == DisplayMode.GUI){
            new CreateHeroGUIController(new CreateHeroGUIView(), new CreateHeroModel());
        }else{
            new CreateHeroController(new CreateHeroView(), new CreateHeroModel());
        }
    }

    public static void StartGame(Hero hero){
        if (Cache.Args.DISPLAY_MODE == DisplayMode.GUI){
            new GameGUIController(new GameGUIView(), new GameModel(hero));
        }else{
            new GameController(new GameView(), new GameModel(hero));
        }
    }

    public static void StartSimulation(Hero hero, HeroEnemy enemy){
        if (Cache.Args.DISPLAY_MODE == DisplayMode.GUI){
            SwingyIO.GUIOut("StartSimulation()");
        }else{
            new FightSimulationController(new FightSimulationView(), new FightSimulationModel(hero, enemy));
        }
    }
}