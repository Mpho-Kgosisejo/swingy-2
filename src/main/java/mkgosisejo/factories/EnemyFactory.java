package mkgosisejo.factories;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mkgosisejo.enums.Artifacts;
import mkgosisejo.models.Hero;
import mkgosisejo.models.HeroEnemy;
import mkgosisejo.models.Position;
import mkgosisejo.utils.Formulas;

public class EnemyFactory {
    private static int getRandNum(int num){
        return ((num / 2) + new Random().nextInt(num));
    }

    private static Artifacts getRandomArtifact(){
        return (Artifacts.values()[new Random().nextInt(Artifacts.values().length)]);
    }

    private static HeroEnemy newEnemy(Hero hero){
        HeroEnemy enemy = new HeroEnemy();
        Random random = new Random();
        int mapSize = Formulas.NumberOfEnemiesToSpawn(hero.getLevel());

        enemy.setName("<enemy-name>");
        enemy.setHp(getRandNum(hero.getHp()));
        enemy.setAttack(getRandNum(hero.getAttack()));
        enemy.setDefence(getRandNum(hero.getDefence()));
        enemy.setArtifact(getRandomArtifact());
        enemy.setPosition(new Position(random.nextInt(mapSize), random.nextInt(mapSize)));
        return (enemy);
    }

    public static List<HeroEnemy> GetEnemyList(Hero hero){
        List<HeroEnemy> enemyList = new ArrayList<HeroEnemy>();
        Random random = new Random();
        int numOfEnemies = Formulas.NumberOfEnemiesToSpawn(hero.getLevel());
        int mapSize = Formulas.MapSize(hero.getLevel());
        int id = 1;

        while (numOfEnemies > 0){
            HeroEnemy enemy = newEnemy(hero);
            enemy.setId(id);
            enemy.setPosition(new Position(random.nextInt(mapSize), random.nextInt(mapSize)));
            Position centerPosition = new Position((mapSize / 2), (mapSize / 2));
            
            if (enemy != null && !centerPosition.isEquals(enemy.getPosition()) && !isSamePosition(enemyList, enemy.getPosition())){
                enemyList.add(enemy);
                id++;
            }else{
                numOfEnemies++;
            }
            numOfEnemies--;
        }
        return (enemyList);
    }

    public static boolean isSamePosition(List<HeroEnemy> enemies, Position newPosition){
        if (enemies.size() > 1){
            for (HeroEnemy enemy: enemies) {
                if (enemy.getPosition().isEquals(newPosition)){
                    return (true);
                }
            }
        }
        return (false);
    }
}