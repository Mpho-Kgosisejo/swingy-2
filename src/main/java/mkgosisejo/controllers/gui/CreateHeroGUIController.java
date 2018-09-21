package mkgosisejo.controllers.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mkgosisejo.controllers.AppController;
import mkgosisejo.factories.HeroFactory;
import mkgosisejo.models.CreateHeroModel;
import mkgosisejo.models.Hero;
import mkgosisejo.providers.DataProvider;
import mkgosisejo.utils.Messages;
import mkgosisejo.utils.SwingyIO;
import mkgosisejo.views.gui.CreateHeroGUIView;

public class CreateHeroGUIController {
    private CreateHeroGUIView _view;
    private CreateHeroModel _model;

    public CreateHeroGUIController(CreateHeroGUIView view, CreateHeroModel model){
        this._view = view;
        this._model = model;

        this._view.setCreateHeroListener(new CreateHero());
        this._view.setCancelListener(new Cancel());
        this._view.setVisible(true);
    }

    private void createHero(){
        String name = this._view.getHeroName();
        String type = this._view.getHeroType();
        String artifact = this._view.getHeroArtifact();
        Hero hero = this._model.newHero(type, name, artifact);

        if (name == null || (name.length() < 5 || name.length() > 10)){
            SwingyIO.GUIOut(CreateHeroModel.NAME_ERROR + ". " + Messages.PLEASE_TRY_AGAIN);
        }else if (this._model.IsDuplicate(name)){
            SwingyIO.GUIOut(CreateHeroModel.HERO_EXISTS_ERROR);
        }else{
            if ((new DataProvider().insertHero(hero)) == true){
                SwingyIO.GUIOut(HeroFactory.GetNewHeroStatus());
                AppController.SelectHero();
            }else{
                SwingyIO.GUIOut(CreateHeroModel.ERROR_CREATING_HERO);
                AppController.LandingScreen();
            }
            this._view.dispose();
        }
    }
    
    class CreateHero implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            createHero();
		}
    }

    class Cancel implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            AppController.SelectHero();
            _view.dispose();
		}
    }
}