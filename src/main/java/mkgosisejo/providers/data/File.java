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
    public File(){

    }

    public boolean insertHero() {
        return false;
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

    public boolean updateHero() {
        return false;
    }

	public boolean deleteHero() {
		return false;
	}
}