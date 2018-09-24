package mkgosisejo.controllers.console;

import java.util.Random;

import mkgosisejo.models.FightSimulationModel;
import mkgosisejo.utils.ConsoleHelper;
import mkgosisejo.utils.SwingyIO;
import mkgosisejo.views.console.FightSimulationView;

public class FightSimulationController {
    private FightSimulationModel _model;
    private FightSimulationView _view;

    public FightSimulationController(FightSimulationView view, FightSimulationModel model){
        this._model = model;
        this._view = view;

        this.runSimulation();
    }    

    private void runSimulation(){
        try {
            while (this._model.nextFight()) {
                SwingyIO.ConsoleOutLine(this._model.getSimulation());
                Thread.sleep(FightSimulationModel.SIMULATION_SPEED);
            }

            SwingyIO.ConsoleOutLine();
            if (this._model.heroWon()){
                SwingyIO.ConsoleOutLine(FightSimulationModel.WON_FIGHT);
                ConsoleHelper.PressEnterToContinue();

                if (new Random().nextBoolean()){
                    if (this.pickUpArtifact()){
                        this._model.pickUpArtifact();
                    }
                }
            }else{
                SwingyIO.ConsoleOutLine(FightSimulationModel.LOST_FIGHT);
                ConsoleHelper.PressEnterToContinue();
            }
        } catch (Exception e) {}
    }

    private boolean pickUpArtifact(){
        int selection = this._view.pickUpArtifact(this._model.getDroppedArtifactMessage());

        if (selection == 1){
            return (true);
        }
        else if (selection == 2){
            return (false);
        }
        ConsoleHelper.InvalidInput();
        return (pickUpArtifact());
    }
}