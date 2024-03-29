package mkgosisejo.providers.data;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import mkgosisejo.factories.HeroFactory;
import mkgosisejo.interfaces.IDataProvider;
import mkgosisejo.models.Hero;
import mkgosisejo.utils.DatabaseHandler;
import mkgosisejo.utils.SwingyIO;

public class Database implements IDataProvider {
    private DatabaseHandler _db = null;

    public Database(){
        this._db = new DatabaseHandler("heros");

        if (this._db.getDBStat() == false){
            SwingyIO.ConsoleOutLine("Database Error: " + this._db.getDBStatMessage());
            System.exit(2);
        }
    }

    public boolean insertHero(Hero hero) {
        return this._db.insert(hero);
    }

    public List<Hero> getHeros() {
        List<Hero> heroList = new ArrayList<Hero>();
        
        try {
            ResultSet results = this._db.select();
            Hero hero = null;

            if (results != null){
                while(results.next()){
                    hero = HeroFactory.NewHero(results.getString("type"), results.getString("name"), results.getString("xp"), results.getString("attack"), results.getString("defence"), results.getString("hp"), results.getString("artifact"));
    
                    if (hero != null){
                        heroList.add(hero);
                    }
                }
            }
        } catch (Exception e) {}
        return heroList;
    }

    public boolean updateHero(Hero hero) {
        return this._db.update(hero);
    }

	public boolean deleteHero(Hero hero) {
		return this._db.delete(hero.getId()             );
	}
}