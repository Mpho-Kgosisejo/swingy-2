package mkgosisejo.providers.data;

import java.util.ArrayList;
import java.util.List;

import mkgosisejo.config.ConfigApp;
import mkgosisejo.factories.HeroFactory;
import mkgosisejo.interfaces.IDataProvider;
import mkgosisejo.models.Hero;
import mkgosisejo.providers.cache.Cache;
import mkgosisejo.utils.FileHandler;
import mkgosisejo.utils.SwingyIO;

public class File implements IDataProvider {
    public File(){}

    public boolean insertHero(Hero hero) {
        String text = "";

        for (Hero h: Cache.Data.heroList) {
            text += this.heroRecored(h) + "\n";
        }
        text += this.heroRecored(hero);
        return (FileHandler.WriteFile(ConfigApp.GetPath(Cache.Config.FILE_SOURCE_NAME), text, false));
    }

    public List<Hero> getHeros() {
        List<Hero> heroList = new ArrayList<Hero>();
        String file = null;
        String[] fileLines = null;
        String[] heroFields = null;
        Hero hero = null;
        
        file = FileHandler.ReadFile(ConfigApp.GetPath(Cache.Config.FILE_SOURCE_NAME));
        if (file != null){
            fileLines = file.trim().split("\n");

            for (String line: fileLines) {
                heroFields = line.trim().split(",");

                if (heroFields != null && heroFields.length == 7){
                    hero = HeroFactory.NewHero(heroFields[0], heroFields[1], heroFields[2], heroFields[3], heroFields[4], heroFields[5], heroFields[6]);
                    
                    if (hero != null)
                        heroList.add(hero);
                    else
                        SwingyIO.ConsoleOutLine(HeroFactory.GetNewHeroStatus());
                } 
            }
        }
        return heroList;
    }

    public boolean updateHero(Hero hero) {
        
        return false;
    }

	public boolean deleteHero(Hero hero) {
        String text = "";

        for (Hero h: Cache.Data.heroList) {
            if (!h.equals(hero) && h.getId() != hero.getId()){
                text += this.heroRecored(h) + "\n";
            }
        }
        return (FileHandler.WriteFile(ConfigApp.GetPath(Cache.Config.FILE_SOURCE_NAME), text, false));
    }
    
    private String heroRecored(Hero hero){
        return (String.format("%s,%s,%s,%s,%s,%s,%s", hero.getType().toUpperCase(), hero.getName(), hero.getXp(), hero.getAttack(), hero.getDefence(), hero.getHp(), hero.getArtifact()));
    }
}