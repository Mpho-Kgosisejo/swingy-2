package mkgosisejo.providers.data;

import java.util.List;

import mkgosisejo.interfaces.IDataProvider;
import mkgosisejo.models.Hero;

public class Database implements IDataProvider {
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