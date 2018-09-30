package mkgosisejo.controllers.console;

import mkgosisejo.controllers.AppController;
import mkgosisejo.enums.DisplayMode;
import mkgosisejo.models.CreateHeroModel;
import mkgosisejo.models.Hero;
import mkgosisejo.models.SelectHeroModel;
import mkgosisejo.utils.ConsoleHelper;
import mkgosisejo.utils.Messages;
import mkgosisejo.utils.SwingyIO;
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

    private void selectHero(){
        int selection = this._view.showHeros(this._model.getHeroList());
        Hero hero = this._model.getSelectedHero(selection - 1);
        
        if (hero != null){
            this.heroSelected(hero);
        }
        else if (selection == (this._model.getHeroList().size() + 1)){
            AppController.CreateHero();
        }
        else if (selection == (this._model.getHeroList().size() + 2)){
            DisplayMode.SwitchDisplay();
            AppController.SelectHero();
        }
        else if (selection == (this._model.getHeroList().size() + 3)){
            ConsoleHelper.ExitSwingy();
        }
        else{
            ConsoleHelper.InvalidInput();
            this.selectHero();
        }
    }

    private void heroSelected(Hero hero){
        int selection = this._view.showHeroDetails(hero);

        if (selection == 1){
            AppController.StartGame(hero);
        }
        else if (selection == 2){
            this.selectHero();
        }
        else if (selection == 3){
            this.updateHero(hero);
        }
        else if (selection == 4){
            this.deleteHero(hero);
        }else{
            ConsoleHelper.InvalidInput();
            this.heroSelected(hero);
        }
    }

    private void updateHero(Hero hero){
        hero.setName(this.getName());

        if (this._model.updateHero(hero) == true){
            this._view.showSuccessMssg(SelectHeroModel.STR_UPDATE);
        }else{
            this._view.showErrorMssg(SelectHeroModel.STR_UPDATING);
        }
        AppController.LandingScreen();
    }

    private String getName(){
        String name = this._view.getName().trim();

        if (name.length() < 5 || name.length() > 10){
            SwingyIO.ConsoleOutLine(CreateHeroModel.NAME_ERROR + ". " + Messages.PLEASE_TRY_AGAIN);
            ConsoleHelper.PressEnterToContinue();
            return (this.getName());
        }else if (CreateHeroModel.IsDuplicate(name)){
            SwingyIO.ConsoleOutLine(CreateHeroModel.HERO_EXISTS_ERROR);
            ConsoleHelper.PressEnterToContinue();
            return (this.getName());
        }
        return (name);
    }

    private void deleteHero(Hero hero){
        int option = this._view.showDeleteHero();

        if (option == 1){
            if (this._model.deleteHero(hero) == true){
                this._view.showSuccessMssg(SelectHeroModel.STR_DELETION);
            }else{
                this._view.showErrorMssg(SelectHeroModel.STR_DELETING);
            }
            AppController.LandingScreen();
        }
        else if (option == 2){
            this.heroSelected(hero);
        }else{
            ConsoleHelper.InvalidInput();
            this.deleteHero(hero);
        }
    }
}