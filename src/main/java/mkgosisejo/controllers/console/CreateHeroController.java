package mkgosisejo.controllers.console;

import mkgosisejo.controllers.AppController;
import mkgosisejo.enums.Artifacts;
import mkgosisejo.enums.HeroTypes;
import mkgosisejo.factories.HeroFactory;
import mkgosisejo.models.CreateHeroModel;
import mkgosisejo.models.Hero;
import mkgosisejo.providers.DataProvider;
import mkgosisejo.utils.ConsoleHelper;
import mkgosisejo.utils.Messages;
import mkgosisejo.utils.SwingyIO;
import mkgosisejo.views.console.CreateHeroView;

public class CreateHeroController {
    CreateHeroView _view;
    CreateHeroModel _model;

    public CreateHeroController(CreateHeroView view, CreateHeroModel model){
        this._view = view;
        this._model = model;

        this.createHero();
    }

    private void createHero(){
        String name = this.getName();
        String type = this.getType();
        String artifact = this.getArtifact();
        Hero hero = this._model.newHero(type, name, artifact);

        if ((new DataProvider().insertHero(hero)) == true){
            SwingyIO.ConsoleOutLine(HeroFactory.GetNewHeroStatus());
            ConsoleHelper.PressEnterToContinue();
        }
        AppController.LandingScreen();
    }

    private String getName(){
        String name = this._view.getName().trim();

        if (name.length() < 5 || name.length() > 10){
            SwingyIO.ConsoleOutLine(CreateHeroModel.NAME_ERROR + ". " + Messages.PLEASE_TRY_AGAIN);
            ConsoleHelper.PressEnterToContinue();
            return (this.getName());
        }else if (this._model.IsDuplicate(name)){
            SwingyIO.ConsoleOutLine(CreateHeroModel.HERO_EXISTS_ERROR);
            ConsoleHelper.PressEnterToContinue();
            return (this.getName());
        }
        return (name);
    }

    private String getType(){
        int selection = (this._view.getType() - 1);

        if (selection < 0 || selection >= HeroTypes.values().length){
            ConsoleHelper.InvalidInput();
            return (this.getType());
        }
        return (HeroTypes.values()[selection].toString());
    }

    private String getArtifact(){
        int selection = (this._view.getArtifact() - 1);

        if (selection < 0 || selection >= Artifacts.values().length){
            ConsoleHelper.InvalidInput();
            return (this.getArtifact());
        }
        return (Artifacts.values()[selection].toString());
    }
}