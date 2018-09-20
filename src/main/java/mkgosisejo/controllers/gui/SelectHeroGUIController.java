package mkgosisejo.controllers.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import mkgosisejo.controllers.AppController;
import mkgosisejo.models.Hero;
import mkgosisejo.models.SelectHeroModel;
import mkgosisejo.utils.SwingyIO;
import mkgosisejo.views.gui.SelectHeroGUIView;

public class SelectHeroGUIController {
    private SelectHeroGUIView _view;
    private SelectHeroModel _model;
    
    public SelectHeroGUIController(SelectHeroGUIView view, SelectHeroModel model){
        this._view = view;
        this._model = model;

        this._view.setBtnCreateHeroListener(new CreateHero());
        this._view.setBtnSelectHeroListener(new SelectHero());
        this._view.setBtnExitListener(new Exit());
        this._view.setListHeroListener(new HeroSelected());

        this.initHeroList();

        this._view.setVisible(true);
    }

    private void initHeroList(){
        int i = 0;
        String[] herosArray = new String[this._model.getHeroList().size()];

        for (Hero hero: this._model.getHeroList()) {
            herosArray[i] = String.format("%d. %10.10s - %6.6s - %s", (i + 1), hero.getName(), hero.getType(), (hero.getLevel() + "(" + hero.getXp() + "XP)"));
            i++;
        }
        this._view.setHeroList(herosArray);
    }

    private class CreateHero implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            AppController.CreateHero();
            _view.dispose();
		}
    }

    private class SelectHero implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            SwingyIO.ConsoleOutLine("SelectHero{}");
		}
    }

    private class Exit implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            _view.dispose();
		}
    }

    private class HeroSelected implements MouseListener {
        public void mouseClicked(MouseEvent e) {
            Hero hero = _model.getSelectedHero(_view.getListIndex());

            if (hero != null && _view.getListIndex() >= 0){
                _view.setHeroNameTitle(hero.getName());
                _view.setHeroTextDetails(SelectHeroModel.GetHeroDetails(hero));
                _view.enableSelectHeroBtn();
            }
        }

        public void mousePressed(MouseEvent e) {}

        public void mouseReleased(MouseEvent e) { }

        public void mouseEntered(MouseEvent e) {}

        public void mouseExited(MouseEvent e) {}
    }
}