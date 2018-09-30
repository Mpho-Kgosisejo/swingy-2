package mkgosisejo.controllers.gui;

import java.util.Random;

import mkgosisejo.controllers.AppController;
import mkgosisejo.models.FightSimulationModel;
import mkgosisejo.utils.SwingyIO;
import mkgosisejo.views.gui.GameGUIView;

public class FightSimulationGUIController {
    private FightSimulationModel _model;
    private GameGUIView _view;
    private String _simText = "";

    public FightSimulationGUIController(GameGUIView view, FightSimulationModel model){
        this._model = model;
        this._view = view;

        this._view.setSize(750, 250);
        this._view.setVisible(true);

        // new Thread(new Runnable(){
        //     public void run() {
                runSimulation();
        //     }
        // }).start();
    } 

    private void runSimulation(){
        try {
            this._view.setTitle(this._model.getVSTitle());
            Thread.sleep(1000);
            while (this._model.nextFight()) {
                this._view.setTitle(this._model.getVSTitle());
                this._simText += this._model.getSimulation() + "\n";
                this._view.setGameText(this._simText);
                // Thread.sleep(FightSimulationModel.SIMULATION_SPEED);
            }

            if (this._model.heroWon()){
                SwingyIO.GUIOut(FightSimulationModel.WON_FIGHT);

                if (new Random().nextBoolean()){
                    if (SwingyIO.GUIConfirm(this._model.getDroppedArtifactMessage())){
                        this._model.pickUpArtifact();
                    }
                }
            }else{
                SwingyIO.GUIOut(FightSimulationModel.LOST_FIGHT);
            }
        } catch (Exception e) {}
        this._view.dispose();
    }
}