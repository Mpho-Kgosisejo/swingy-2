package mkgosisejo.views.gui;

import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import mkgosisejo.utils.GUIHelper;

public class GameGUIView extends JFrame{    
    private JTextArea _textArea;

    public GameGUIView(){
        GUIHelper.SetDefaultFrame(this, "Game", 550, 550);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.init();
    }

    private void init(){
        this._textArea = new JTextArea();
        this._textArea.setEnabled(false);

        this.add(this._textArea);
    }

    public void setKeyListener(KeyListener listener){
        this.addKeyListener(listener);
    }

    public void setGameText(String text){
        this._textArea.setText(text);
    }

    public void setGameTitle(String title){
        this.setTitle(title);
    }
}