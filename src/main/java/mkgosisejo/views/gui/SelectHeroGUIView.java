package mkgosisejo.views.gui;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import mkgosisejo.utils.GUIHelper;

public class SelectHeroGUIView extends JFrame {
    private JPanel panelRight;
    private JPanel panelRightTop;
    private JList<String> lstHeroNames;
    private JButton btnCreateHero;
    private JButton btnUpdateHero;
    private JLabel lblHeroName;
    private JTextArea txtAHeroInfo;
    private JButton btnSelectHero;
    private JLabel lblHeroNameTitle;
        
    public SelectHeroGUIView(){
        GUIHelper.SetDefaultFrame(this, "Select Hero", 600, 300);
        this.init();
    }

    private void init(){
        this.panelRight = new JPanel();
        JPanel panelMain = new JPanel();
        JPanel panelLeft = new JPanel();
        JPanel panelLeftControllers = new JPanel();
        this.panelRightTop = new JPanel();
        JPanel panelRightMid = new JPanel();
        JPanel panelRightBottom = new JPanel();
        this.lstHeroNames = new JList<String>();
        this.btnCreateHero = new JButton("Create New Hero");
        this.btnUpdateHero = new JButton("Update Hero");
        this.btnUpdateHero.setEnabled(false);
        this.lblHeroName = new JLabel();
        this.txtAHeroInfo = new JTextArea();
        this.btnSelectHero = new JButton("Select Hero");
        panelMain.setLayout(new GridLayout(1, 2));
        panelMain.setSize(new Dimension(this.getWidth(), this.getHeight()));
        panelLeft.setLayout(null);
        this.panelRight.setLayout(null);
        this.lstHeroNames.setBounds(10, 10, ((panelMain.getWidth() / 2) - 20), 230);
        panelLeftControllers.setBounds(0, 240, (panelMain.getWidth() / 2), 60);
        this.panelRightTop.setBounds(0, 0, (panelMain.getWidth() / 2), 70);
        panelRightMid.setBounds(0, 70, (panelMain.getWidth() / 2), 170);
        panelRightMid.setLayout(null);
        panelRightBottom.setBounds(0, 240, (panelMain.getWidth() / 2), 50);
        this.txtAHeroInfo.setBounds(10, 0, ((panelMain.getWidth() / 2) - 20), 170);
        this.txtAHeroInfo.setEditable(false);
        this.lblHeroNameTitle = new JLabel();
        this.lblHeroNameTitle.setSize(10, 10);

        this.panelRightTop.add(this.lblHeroNameTitle);
        panelLeftControllers.add(this.btnCreateHero);
        panelLeftControllers.add(this.btnUpdateHero);
        panelLeft.add(this.lstHeroNames);
        panelLeft.add(panelLeftControllers);
        this.panelRightTop.add(this.lblHeroName);
        panelRightMid.add(this.txtAHeroInfo);
        panelRightBottom.add(this.btnSelectHero);
        panelMain.add(panelLeft);
        panelMain.add(this.panelRight);
        panelRight.add(this.panelRightTop);
        panelRight.add(panelRightMid);
        panelRight.add(panelRightBottom);
        this.btnSelectHero.setEnabled(false);
        this.add(panelMain);
    }

    public void setBtnCreateHeroListener(ActionListener listener){
        this.btnCreateHero.addActionListener(listener);
    }

    public void setbtnUpdateHeroListener(ActionListener listener){
        this.btnUpdateHero.addActionListener(listener);
    }

    public void setBtnSelectHeroListener(ActionListener listener){
        this.btnSelectHero.addActionListener(listener);
    }

    public void setListHeroListener(MouseListener listener){
        this.lstHeroNames.addMouseListener(listener);
    }

    public void setHeroList(String[] heros){
        this.lstHeroNames.setListData(heros);
    }

    public void setHeroTextDetails(String text){
        this.txtAHeroInfo.setText(text);
    }

    public void setHeroNameTitle(String name){
        this.lblHeroNameTitle.setText(name);
    }

    public void enableUpdateHeroBtn(){
        this.btnUpdateHero.setEnabled(true);
    }

    public void enableSelectHeroBtn(){
        this.btnSelectHero.setEnabled(true);
    }

    public int getListIndex(){
        return (this.lstHeroNames.getSelectedIndex());
    }
}