package mkgosisejo.models;

import java.util.Random;

import mkgosisejo.utils.Formulas;

public class FightSimulationModel {
    private Hero _hero;
    private HeroEnemy _enemy;
    private String _simulation;
    private Random _random;
    private static int _count;
    public static String WON_FIGHT = "You WON the Fight against the Enemy";
    public static String LOST_FIGHT = "You LOST the Fight against the Enemy";
    private static String DROPPED_ARTIFACT = "Enenmy has droped an artifact";
    public static String PICK_UP_ARTIFACT = "Do you wish to pick up the artifact?";
    public static int SIMULATION_SPEED = 50;

    public FightSimulationModel(Hero hero, HeroEnemy enemy){
        this._hero = hero;
        this._enemy = enemy;

        _count = 0;
        this._random = new Random();
    }

    public boolean nextFight() throws Exception{
        this._simulation = "";
        Hero attacker = null;
        Hero defender = null;
        int attack = 0;
        String super_power = "";
        int super_power_num = 0;

        if (this._hero.isAlive() && this._enemy.isAlive()){
            if (this.getRandom()){
                attacker = this._hero;
                defender = this._enemy;
            }else{
                attacker = this._enemy;
                defender = this._hero;
            }

            if (attacker.getEnergy() > 0){
                if (this.getRandom()){
                    super_power = "<Super Power>";
                    super_power_num = this._random.nextInt((attacker.getEnergy() + 1));
                    attacker.setEnergy(fixAttack(attacker.getEnergy() - super_power_num));

                    attack = (((super_power_num + 1) * 2) + attacker.getAttack());
                }else{
                    attack = attacker.getAttack();
                }
            }else{
                attack = attacker.getAttack();
            }
            attack = (attack - defender.getDefence());
            attack = fixAttack(attack);
            _count++;

            defender.setHp(defender.getHp() - attack);
            attacker.setEnergy(this.increaseEnergy(attacker.getEnergy()));
            this._simulation = String.format("%3.3s. (%10.10s %4.4sHP #%3.3s) attacks (%10.10s %4.4sHP #%3.3s) with damage of %d %s", _count, attacker.getName(), attacker.getHp(), attacker.getEnergy(), defender.getName(), defender.getHp(), defender.getEnergy(), attack, super_power);
            return (true);
        }
        return (false);
    }

    private int increaseEnergy(int energy){
        energy += (this._random.nextInt(10) + 2);
        if (energy >= GameModel.HEROs_MAX_ENERGY)
            return (new Integer(GameModel.HEROs_MAX_ENERGY));
        return (energy);
    }

    private boolean getRandom(){
        return (this._random.nextBoolean());
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

    public Hero getHero(){
        return (this._hero);
    }

    public void pickUpArtifact(){
        this._hero.setArtifact(this._enemy.getArtifact());
    }

    public String getDroppedArtifactMessage(){
        return (DROPPED_ARTIFACT + ": " + this._enemy.getArtifact());
    }

    public String getVSTitle(){
        return (String.format("%s %sHP (#%3.3s Energy) VS %s %sHP (#%3.3s Energy)", this._hero.getName(), this._hero.getHp(), this._hero.getEnergy(), this._enemy.getName(), this._enemy.getHp(), this._enemy.getEnergy()));
    }
}