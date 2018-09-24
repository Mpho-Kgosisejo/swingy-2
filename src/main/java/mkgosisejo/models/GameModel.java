package mkgosisejo.models;

import java.util.List;
import java.util.Random;

import mkgosisejo.controllers.AppController;
import mkgosisejo.enums.Moves;
import mkgosisejo.factories.EnemyFactory;
import mkgosisejo.providers.DataProvider;
import mkgosisejo.providers.cache.Cache;
import mkgosisejo.providers.data.Database;
import mkgosisejo.utils.Formulas;

public class GameModel {
    public static String YOU_HAVE_ENCOUNTERED_AN_ENEMY = "You have encountered an enemy";
    public static String FIGHT_OR_RUN = "Fight(Yes) or Run(No)";
    public static String RUN_SUCCESS = "Lucky You! The enemy doesn't feel like fighting eighter";
    public static String RUN_FAIL = "Unfortunately luck is not on your side, you must fight the Enemy... :-(";
    public static String GAME_OVER = "!GAME OVER!\nWe knew you'll never make it! ... weak being.";
    public static String WON_BY_ESCAPE = "!YOU WON!\nYou escaped, nice job... bet you can't do it again.";
    public static String WON_BY_DEFEATING_ENEMIES = "!YOU WON!\nYou defeated all enemies... WOW! You must have cheated. Play again and prove us wrong \"You Weak Being\"!";
    private String _map;
    private Hero _hero;
    private int _mapSize;
    private HeroEnemy _enemy = null;
    private List<HeroEnemy> _enemyList;
    private boolean _collision = false;
    private Position _oldPosition = null;
    private int _hpCopy;

    public GameModel(Hero hero){
        this._hero = hero;

        this.init();
    }

    private void init(){
        this._mapSize = Formulas.MapSize(_hero.getLevel());
        this._enemyList = EnemyFactory.GetEnemyList(this._hero);
        this._hpCopy = this._hero.getHp();
        _hero.setPosition(new Position((this._mapSize / 2), (this._mapSize / 2)));
    }

    public void drawMap(){
        Position loopPosition = new Position();
        boolean heroPosition = false;
        boolean enemyPosition = false;
        String mapChar = "* ";
        
        this._map = "";
        this._collision = false;

        for (int x = 0; x < this._mapSize; x++){
            for (int y = 0; y < this._mapSize; y++){
                loopPosition.setXY(x, y);
                heroPosition = false;
                enemyPosition = false;
                
                if (loopPosition.isEquals(this._hero.getPosition())){
                    heroPosition = true;
                }
                for (HeroEnemy enemy: this._enemyList) {
                    if (loopPosition.isEquals(enemy.getPosition()) && enemy.isAlive()){
                        enemyPosition = true;
                        if (heroPosition ){
                            this._enemy = enemy;
                        }
                        break ;
                    }
                }
                
                if (heroPosition && enemyPosition){
                    this._map += "! ";
                    this._collision = true;
                }
                else if (heroPosition){
                    this._map += "H ";
                }
                else if (enemyPosition && Cache.Config.IS_DEVELOPMENT){
                    this._map += "E ";
                }
                else{
                    this._map += mapChar;
                }
            }
            this._map += "\n";
        }
    }

    public void moveHero(Moves move){
        this._oldPosition = this._hero.getPosition().getCopy();

        switch (move) {
            case UP:
                this._hero.getPosition().moveUp();
            break;
            case RIGHT:
                this._hero.getPosition().moveRight();
            break;
            case DOWN:
                this._hero.getPosition().moveDown();
            break;
            case LEFT:
                this._hero.getPosition().moveLeft();
            break;
        }
    }

    private void rewardHero(){
        int xp = (Formulas.GetWinGameReward(this._hero.getXp()) + this._hero.getXp());

        if (this.defeatedEnemies()){
            this._hero.setXp(xp + 842);
        }
        else if (this.isHeroAlive()){
            this._hero.setXp(xp);
        }
    }

    public void restoreHero(){
        this._hero.setHp(this._hpCopy);
        this.rewardHero();
        new DataProvider().updateHero(this._hero);
    }

    public String getGameTitle(){
        String text = String.format("Hero: %s %dHP\n", this._hero.getName(), this._hero.getHp());
        if (Cache.Config.IS_DEVELOPMENT){
            text += String.format("X&Y: %d-%d %d-%d", this._hero.getPosition().getX(), this._mapSize, this._hero.getPosition().getY(), this._mapSize);
        }
        return (text);
    }

    public String getEnemyStats(){
        String stats = "";
        stats += String.format(" %9.9s | %s\n", "Name", this._enemy.getName());
        stats += String.format(" %9.9s | %s\n", "Attack", this._enemy.getAttack());
        stats += String.format(" %9.9s | %s\n", "Defence", this._enemy.getDefence());
        stats += String.format(" %9.9s | %s\n", "HP", this._enemy.getHp());
        stats += String.format(" %9.9s | %s", "Artifact", this._enemy.getArtifact());
        return (stats);
    }

    public String getMap(){
        String map = this._map;
        this._map = null;
        return (map);
    }

    public void doFight(){
        AppController.StartSimulation(this._hero, this._enemy);
        this._enemy = null;
    }

    public void toPreviousPosition(){
        this._hero.setPosition(this._oldPosition);
    }

    public boolean fightOrRun(){
        return (new Random().nextBoolean());
    }

    public boolean defeatedEnemies(){
        boolean defeat = true;
        for (HeroEnemy enemy: this._enemyList) {
            if (enemy.isAlive()){
                defeat = false;
            }
        }
        return (defeat);
    }

    public boolean nextFight(){
        return (this.isHeroAlive() && !this.isOutsideMap() && !this.defeatedEnemies());
    }

    public boolean isCollision(){
        return (this._collision);
    };

    public boolean isHeroAlive(){
        return (this._hero.isAlive());
    }

    public boolean isOutsideMap(){
        return ((this._hero.getPosition().getX() < 0 || this._hero.getPosition().getY() < 0) || (this._hero.getPosition().getX() >= this._mapSize || this._hero.getPosition().getY() >= this._mapSize));
    }
}