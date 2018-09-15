package mkgosisejo.controllers.console;

import mkgosisejo.controllers.AppController;
import mkgosisejo.enums.DisplayMode;
import mkgosisejo.utils.ConsoleHelper;
import mkgosisejo.views.console.LandingScreenView;

public class LandingScreenController {
    LandingScreenView _view;

    public LandingScreenController (LandingScreenView view){
        this._view = view;

        this.doUserSelection();
    }

    private void doUserSelection(){
        int selection = this._view.SelectOrCreateHero();

        if (selection == 1){
            AppController.CreateHero();
        }
        else if (selection == 2){
            AppController.SelectHero();
        }
        else if (selection == 3){
            DisplayMode.SwitchDisplay();
            AppController.LandingScreen();
        }
        else if (selection == 4){
            ConsoleHelper.ExitSwingy();
        }
        else{
            ConsoleHelper.InvalidInput();
            this.doUserSelection();
        }
    }
}