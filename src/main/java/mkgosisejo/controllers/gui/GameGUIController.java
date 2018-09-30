package mkgosisejo.controllers.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import mkgosisejo.controllers.AppController;
import mkgosisejo.enums.Moves;
import mkgosisejo.models.GameModel;
import mkgosisejo.utils.Messages;
import mkgosisejo.utils.SwingyIO;
import mkgosisejo.views.gui.GameGUIView;

public class GameGUIController {
    private GameGUIView _view;
    private GameModel _model;

    public GameGUIController(GameGUIView view, GameModel model){
        this._model = model;
        this._view = view;

        this.runGame();

        this._view.setKeyListener(new PressedKeyListener());
        this._view.setVisible(true);
    }

    private void runGame(){
        if (this._model.nextFight()){    
            this._model.drawMap();

            this._view.setGameTitle(this._model.getGameTitle());
            this._view.setGameText(this._model.getMap());

            if (this._model.isCollision()){
                if (SwingyIO.GUIConfirm(GameModel.YOU_HAVE_ENCOUNTERED_AN_ENEMY + "\n\n" + this._model.getEnemyStats()) == true){
                    // Fight...
                    this._model.doFight();
                    this.runGame();
                }else{
                    // Run...
                    if (this._model.fightOrRun()){
                        // Run Success...
                        SwingyIO.GUIOut(GameModel.RUN_SUCCESS);
                        this._model.toPreviousPosition();
                    }else{
                        // Must Fight...
                        SwingyIO.GUIOut(GameModel.RUN_FAIL);
                        this._model.doFight();
                    }
                    this.runGame();
                }
            }
        }else{
            if (this._model.isOutsideMap()){
                SwingyIO.GUIOut(GameModel.WON_BY_ESCAPE);
            }else if (this._model.defeatedEnemies()){
                SwingyIO.GUIOut(GameModel.WON_BY_DEFEATING_ENEMIES);
            }else{
                SwingyIO.GUIOut(GameModel.GAME_OVER);
            }
            
            this._model.restoreHero();
            this._view.dispose();
            AppController.SelectHero();
        }
    }

    class PressedKeyListener implements KeyListener {
		public void keyTyped(KeyEvent e) {}
        public void keyPressed(KeyEvent e) {}
        
		public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();

            switch (key) {
                case 38:
                    _model.moveHero(Moves.UP);    
                break;
                case 40:
                _model.moveHero(Moves.DOWN);   
                break;
                case 37:
                    _model.moveHero(Moves.LEFT);
                break;
                case 39:
                    _model.moveHero(Moves.RIGHT);    
                break;
                default:
                    SwingyIO.GUIOut(Messages.INVALID_INPUT + ". Use: up, left, right and down to move hero");
                break;
            }
            runGame();
        }
    }
}