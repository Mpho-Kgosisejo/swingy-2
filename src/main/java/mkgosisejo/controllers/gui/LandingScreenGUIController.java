package mkgosisejo.controllers.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import mkgosisejo.controllers.AppController;
import mkgosisejo.enums.DisplayMode;
import mkgosisejo.views.gui.LandingScreenGUIView;

public class LandingScreenGUIController {
    private LandingScreenGUIView _view;

    public LandingScreenGUIController(LandingScreenGUIView view){
        this._view = view;
        this._view.setVisible(true);

        this._view.setSelectHeroListener(new SelectHeroListener());
        this._view.setCreateHeroListener(new CreateHeroListener());
        this._view.addWindowListener(new WindowAction());
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

    class WindowAction implements WindowListener {
        public void windowOpened(WindowEvent e) {}

        public void windowClosing(WindowEvent e) {
            _view.dispose();
            DisplayMode.SwitchDisplay();
            AppController.LandingScreen();
        }

        public void windowClosed(WindowEvent e) {}

        public void windowIconified(WindowEvent e) {}

        public void windowDeiconified(WindowEvent e) {}

        public void windowActivated(WindowEvent e) {}

        public void windowDeactivated(WindowEvent e) {}
    }
}