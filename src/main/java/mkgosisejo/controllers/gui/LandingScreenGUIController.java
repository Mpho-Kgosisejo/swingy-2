package mkgosisejo.controllers.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mkgosisejo.controllers.AppController;
import mkgosisejo.views.gui.LandingScreenGUIView;

public class LandingScreenGUIController {
    private LandingScreenGUIView _view;

    public LandingScreenGUIController(LandingScreenGUIView view){
        this._view = view;
        this._view.setVisible(true);

        this._view.setSelectHeroListener(new SelectHeroListener());
        this._view.setCreateHeroListener(new CreateHeroListener());
    }
    
    public class SelectHeroListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            AppController.SelectHero();
            _view.dispose();
		}    
    }

    public class CreateHeroListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            AppController.CreateHero();
            _view.dispose();
        }
    }
}