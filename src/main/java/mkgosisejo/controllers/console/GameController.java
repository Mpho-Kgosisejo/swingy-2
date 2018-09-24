package mkgosisejo.controllers.console;

import mkgosisejo.controllers.AppController;
import mkgosisejo.enums.Moves;
import mkgosisejo.models.GameModel;
import mkgosisejo.utils.ConsoleHelper;
import mkgosisejo.utils.SwingyIO;
import mkgosisejo.views.console.GameView;

public class GameController {
    private GameView _view;
    private GameModel _model;

    public GameController(GameView view, GameModel model){
        this._view = view;
        this._model = model;

        this.runGame();
    }

    private void runGame(){
        while (this._model.nextFight()){
            this._model.drawMap();

            SwingyIO.ConsoleClear();
            SwingyIO.ConsoleOutLine(this._model.getGameTitle());
            SwingyIO.ConsoleOutLine();
            SwingyIO.ConsoleOutLine(this._model.getMap());

            if (this._model.isCollision()){
                if (this.fightOrRun() == true){
                    // Fight...
                    this._model.doFight();
                }else{
                    // Run...
                    if (this._model.fightOrRun()){
                        // Run Success...
                        SwingyIO.ConsoleOutLine(GameModel.RUN_SUCCESS);
                        ConsoleHelper.PressEnterToContinue();
                        this._model.toPreviousPosition();
                    }else{
                        // Must Fight...
                        SwingyIO.ConsoleOutLine(GameModel.RUN_FAIL);
                        ConsoleHelper.PressEnterToContinue();
                        this._model.doFight();
                    }
                }
            }else{
                this._model.moveHero(this.getMove());
            }
        }

        SwingyIO.ConsoleOutLine();
        if (this._model.isOutsideMap()){
            SwingyIO.ConsoleOutLine(GameModel.WON_BY_ESCAPE);
        }else if (this._model.defeatedEnemies()){
            SwingyIO.ConsoleOutLine(GameModel.WON_BY_DEFEATING_ENEMIES);
        }else{
            SwingyIO.ConsoleOutLine(GameModel.GAME_OVER);
        }
        ConsoleHelper.PressEnterToContinue();
        this._model.restoreHero();
        SwingyIO.ConsoleClear();
        AppController.SelectHero();
    }

    private boolean fightOrRun(){
        int selection = this._view.getFightOrRun(this._model.getEnemyStats());

        if (selection == 1){
            return (true);
        }
        else if (selection == 2){
            return (false);
        }
        ConsoleHelper.InvalidInput();
        return (this.fightOrRun());
    }

    private Moves getMove(){
        int selection = (this._view.getMove() - 1);

        if (selection == 0){
            return (Moves.UP);
        }
        else if (selection == 1){
            return (Moves.RIGHT);
        }
        else if (selection == 2){
            return (Moves.DOWN);
        }
        else if (selection == 3){
            return (Moves.LEFT);
        }
        ConsoleHelper.InvalidInput();
        return (this.getMove());
    }
}