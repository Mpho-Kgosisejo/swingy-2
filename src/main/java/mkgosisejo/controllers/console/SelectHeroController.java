package mkgosisejo.controllers.console;

import mkgosisejo.controllers.AppController;
import mkgosisejo.enums.DisplayMode;
import mkgosisejo.models.Hero;
import mkgosisejo.models.SelectHeroModel;
import mkgosisejo.utils.ConsoleHelper;
import mkgosisejo.views.console.SelectHeroView;

public class SelectHeroController {
    public SelectHeroView _view;
    public SelectHeroModel _model;

    public SelectHeroController(SelectHeroView view, SelectHeroModel model){
        this._view = view;
        this._model = model;

        if (this._model.getHeroList().size() > 0){
            this.selectHero();
        }else{
            this._view.showNoHerosMssg();
            AppController.LandingScreen();
        }
    }

    public void selectHero(){
        int selection = this._view.showHeros(this._model.getHeroList());
        Hero hero = this._model.getSelectedHero(selection - 1);
        
        if (hero != null){
            this.heroSelected(hero);
        }
        else if (selection == (this._model.getHeroList().size() + 1)){
            DisplayMode.SwitchDisplay();
            AppController.SelectHero();
        }
        else if (selection == (this._model.getHeroList().size() + 2)){
            ConsoleHelper.ExitSwingy();
        }
        else{
            ConsoleHelper.InvalidInput();
            this.selectHero();
        }
    }

    public void heroSelected(Hero hero){
        int selection = this._view.showHeroDetails(hero);

        if (selection == 1){
            new GameController();
        }
        else if (selection == 2){
            this.selectHero();
        }
        else if (selection == 3){
            if (this._model.updateHero(hero) == true){
                this._view.showSuccessMssg(SelectHeroView.STR_UPDATE);
            }else{
                this._view.showErrorMssg(SelectHeroView.STR_UPDATING);
            }
            AppController.LandingScreen();
        }
        else if (selection == 4){
            if (this._model.deleteHero(hero) == true){
                this._view.showSuccessMssg(SelectHeroView.STR_DELETION);
            }else{
                this._view.showErrorMssg(SelectHeroView.STR_DELETING);
            }
            AppController.LandingScreen();
        }else{
            ConsoleHelper.InvalidInput();
            this.heroSelected(hero);
        }
    }
}