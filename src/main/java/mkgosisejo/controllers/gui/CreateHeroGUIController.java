package mkgosisejo.controllers.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mkgosisejo.controllers.AppController;
import mkgosisejo.enums.Artifacts;
import mkgosisejo.enums.HeroTypes;
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
    private DataProvider _provider;

    public CreateHeroGUIController(CreateHeroGUIView view, CreateHeroModel model){
        this._view = view;
        this._model = model;
        this._provider = new DataProvider();

        if (this._model.isUpdate() == true){
            // Update Hero...
            this._view.setUpUpdate(Artifacts.getValueOfIndex(this._model.getHero().getArtifact().toString()), HeroTypes.getValueOfIndex(this._model.getHero().getType()));
            this._view.setHeroName(this._model.getHero().getName());
        }else{
            // Create Hero...
            this._view.setUpCreate();
        }
        this._view.setCreateHeroListener(new CreateHero());
        this._view.setUpdateHeroListener(new UpdateHero());
        this._view.setDeleteHeroListener(new DeleteHero());
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

    private void updateHero(Hero hero){
        String newName = this._view.getHeroName().trim();

        if (this._model.IsDuplicate(newName) == false){
            hero.setName(newName);

            if (newName.length() < 5 || newName.length() > 10){
                SwingyIO.GUIOut(CreateHeroModel.NAME_ERROR);
            }else{
                if (this._provider.updateHero(hero) == true){
                    SwingyIO.GUIOut(Messages.HERO_UPDATED_SUCCESS);
                    _view.dispose();
                    AppController.SelectHero();
                }else{
                    SwingyIO.GUIOut(Messages.ERROR_UPDATING_HERO);
                }
            }
        }else{
            SwingyIO.GUIOut(CreateHeroModel.HERO_EXISTS_ERROR);
        }
    }

    private void deleteHero(Hero hero){
        if (SwingyIO.GUIConfirm(Messages.WANT_TO_DELETE_HERO)){
            if (this._provider.deleteHero(hero) == true){
                SwingyIO.GUIOut(Messages.HERO_DELETE_SUCCESS);
                _view.dispose();
                AppController.SelectHero();
            }else{
                SwingyIO.GUIOut(Messages.ERROR_DELETE_HERO);
            }
        }
    }
    
    private class CreateHero implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            createHero();
		}
    }

    private class Cancel implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            AppController.SelectHero();
            _view.dispose();
		}
    }

    private class UpdateHero implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            updateHero(_model.getHero());
		}
    }

    private class DeleteHero implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            deleteHero(_model.getHero());
		}
    }
}