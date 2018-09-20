package mkgosisejo.views.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import mkgosisejo.utils.GUIHelper;

public class LandingScreenGUIView extends JFrame{
    private JLabel _lblWelcomeMssg;
    private JButton _btnSelectHero;
    private JButton _btnCreateNewHero;

    public LandingScreenGUIView(){
        GUIHelper.SetDefaultFrame(this, "Welcome to Swingy...", 400, 150);
        
        this.init();
    }

    private void init(){
        this._lblWelcomeMssg = new JLabel("Welcome to Swingy...", SwingConstants.LEFT);
        this._btnSelectHero = new JButton("Select Hero");
        this._btnCreateNewHero = new JButton("Create Hero");

        JPanel panelMain = new JPanel();
        JPanel panelTop = new JPanel();
        JPanel panelBottom = new JPanel();

        panelMain.setLayout(null);
        panelMain.setSize(new Dimension(this.getWidth(), this.getHeight()));
        panelTop.setBackground(new Color(228, 228, 228));
        panelTop.setBounds(0, 0, this.getWidth(), 50);
        panelBottom.setBounds(0, 50, this.getWidth(), 100);

        panelTop.add(this._lblWelcomeMssg);
        panelBottom.add(this._btnSelectHero);
        panelBottom.add(this._btnCreateNewHero);
        panelMain.add(panelTop);
        panelMain.add(panelBottom);

        this.add(panelMain);
    }

    public void setSelectHeroListener(ActionListener action){
        this._btnSelectHero.addActionListener(action);
    }

    public void setCreateHeroListener(ActionListener action){
        this._btnCreateNewHero.addActionListener(action);
    }
}