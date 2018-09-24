package mkgosisejo.models;

import java.util.Random;

import mkgosisejo.utils.Formulas;

public class FightSimulationModel {
    private Hero _hero;
    private HeroEnemy _enemy;
    private String _simulation;
    private static int _count;
    public static String WON_FIGHT = "You WON the Fight against the Enemy";
    public static String LOST_FIGHT = "You LOST the Fight against the Enemy";
    private static String DROPPED_ARTIFACT = "Enenmy has droped an artifact";
    public static String PICK_UP_ARTIFACT = "Do you wish to pick up the artifact?";
    public static int SIMULATION_SPEED = 75;

    public FightSimulationModel(Hero hero, HeroEnemy enemy){
        this._hero = hero;
        this._enemy = enemy;

        _count = 0;
    }

    public boolean nextFight(){
        this._simulation = "";
        Hero attacker = null;
        Hero defender = null;
        int attack = 0;

        if (this._hero.isAlive() && this._enemy.isAlive()){
            if (this.getRandom()){
                attacker = this._hero;
                defender = this._enemy;
            }else{
                attacker = this._enemy;
                defender = this._hero;
            }

            _count++;
            attack = (attacker.getAttack() - defender.getDefence());
            attack = fixAttack(attack);
            defender.setHp(defender.getHp() - attack);
            this._simulation = String.format("%3.3s. (%10.10s %4.4sHP) attacks (%10.10s %4.4sHP) with damage of %d", _count, attacker.getName(), attacker.getHp(), defender.getName(), defender.getHp(), attack);
            return (true);
        }
        return (false);
    }

    private boolean getRandom(){
        return (new Random().nextBoolean());
    }

    public boolean heroWon(){
        int xp = this._hero.getXp();
        this._hero.setXp(xp + Formulas.GetWinFightReward(xp));
        return (this._hero.isAlive());
    }

    private int fixAttack(int num){
        return ((num >= 0) ? num : -1);
    }

    public String getSimulation(){
        String sim = this._simulation;
        this._simulation = "";
        return (sim);
    }

    public void pickUpArtifact(){
        this._hero.setArtifact(this._enemy.getArtifact());
    }

    public String getDroppedArtifactMessage(){
        return (DROPPED_ARTIFACT + ": " + this._enemy.getArtifact());
    }
}