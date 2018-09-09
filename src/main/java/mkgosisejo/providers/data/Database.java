package mkgosisejo.providers.data;

import java.util.List;

import mkgosisejo.interfaces.IDataProvider;
import mkgosisejo.models.Hero;
import mkgosisejo.utils.DatabaseHandler;

public class Database implements IDataProvider {
    private DatabaseHandler _db = null;

    public Database(){
        this._db = new DatabaseHandler();
    }

    public boolean insertHero() {
        return false;
    }

    public List<Hero> getHeros() {
        return null;
    }

    public boolean updateHero() {
        return false;
    }

	public boolean deleteHero() {
		return false;
	}
}