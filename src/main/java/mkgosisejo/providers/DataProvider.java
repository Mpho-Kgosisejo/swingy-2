package mkgosisejo.providers;

import java.util.ArrayList;
import java.util.List;

import mkgosisejo.interfaces.IDataProvider;
import mkgosisejo.models.Hero;
import mkgosisejo.providers.cache.Cache;
import mkgosisejo.providers.data.Database;
import mkgosisejo.providers.data.File;

public class DataProvider implements IDataProvider {
    private IDataProvider dataProvider = null;

    public DataProvider(){
        switch (Cache.Config.DATA_PROVIDER_TYPE){
            case DATABASE:
                dataProvider = new Database();
            break;
            case FILE:
                dataProvider = new File();
            break;
        }
    }

    public boolean insertHero() {
        return (dataProvider.insertHero());
    }

    public List<Hero> getHeros() {
        return (dataProvider.getHeros());
    }

    public boolean updateHero() {
        return (updateHero());
    }

	public boolean deleteHero() {
		return (dataProvider.deleteHero());
	}
}