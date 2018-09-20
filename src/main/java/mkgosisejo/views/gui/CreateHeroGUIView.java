package mkgosisejo.views.gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mkgosisejo.enums.Artifacts;
import mkgosisejo.enums.HeroTypes;
import mkgosisejo.utils.GUIHelper;

public class CreateHeroGUIView extends JFrame {
    private JButton _btnCreateHero;
    private JButton _btnCanel;
    private JTextField _txtFdName;
    private JComboBox<HeroTypes> _listFdType;
    private JComboBox<Artifacts> _listFdArtifact;
    
    public CreateHeroGUIView(){
        GUIHelper.SetDefaultFrame(this, "Create Hero", 400, 360);
    
        this.init();
    }
    
    private void init(){
        JPanel panelMain = new JPanel();
        JPanel panelMid = new JPanel();
        JPanel panelBottom = new JPanel();
        JPanel panelTop1 = new JPanel();
        JLabel _lblName = new JLabel("Name:");
        this._txtFdName = new JTextField(10);
        JPanel panelTop2 = new JPanel();
        JLabel _lblType = new JLabel("Type:");
        this._listFdType = new JComboBox<HeroTypes>(HeroTypes.values());
        JPanel panelTop3 = new JPanel();
        JLabel _lblLevel = new JLabel("Artifact:");
        this._listFdArtifact = new JComboBox<Artifacts>(Artifacts.values());
        this._btnCreateHero = new JButton("Create Hero");
        this._btnCanel = new JButton("Cancel");
    
        panelMain.setLayout(null);
        panelMain.setBounds(0, 0, this.getWidth(), this.getHeight());
        panelMid.setLayout(new GridLayout(3, 2));
        panelMid.setBounds(0, 80, this.getWidth(), 115);
        panelBottom.setBounds(0, 200, this.getWidth(), 38);
    
        panelTop1.add(_lblName);
        panelTop1.add(_txtFdName);
        panelMid.add(panelTop1);
    
        panelTop2.add(_lblType);
        panelTop2.add(_listFdType);
        panelMid.add(panelTop2);
    
        panelTop3.add(_lblLevel);
        panelTop3.add(_listFdArtifact);
        panelMid.add(panelTop3);

        panelBottom.add(this._btnCreateHero);
        panelBottom.add(this._btnCanel);

        panelMain.add(panelMid);
        panelMain.add(panelBottom);
    
        this.add(panelMain);
    }
    
    public void setCreateHeroListener(ActionListener listener){
        this._btnCreateHero.addActionListener(listener);
    }
    
    public void setCancelListener(ActionListener listener){
        this._btnCanel.addActionListener(listener);
    }
    
    public String getHeroName(){
        return (this._txtFdName.getText());
    }

    public String getHeroArtifact(){
        return (this._listFdArtifact.getSelectedItem().toString());
    }
    
    public String getHeroType(){
        return (this._listFdType.getSelectedItem().toString());
    }
}