package mkgosisejo.providers;

import java.util.List;

import mkgosisejo.interfaces.IDataProvider;
import mkgosisejo.models.Hero;
import mkgosisejo.providers.cache.Cache;
import mkgosisejo.providers.data.Database;
import mkgosisejo.providers.data.File;

public class DataProvider implements IDataProvider {
    private IDataProvider _dataProvider = null;

    public DataProvider(){
        switch (Cache.Config.DATA_PROVIDER_TYPE){
            case DATABASE:
                this._dataProvider = new Database();
            break;
            case FILE:
                this._dataProvider = new File();
            break;
        }
    }

    public boolean insertHero() {
        return (this._dataProvider.insertHero());
    }

    public List<Hero> getHeros() {
        return (this._dataProvider.getHeros());
    }

    public boolean updateHero() {
        return (this._dataProvider.updateHero());
    }

	public boolean deleteHero() {
		return (this._dataProvider.deleteHero());
	}
}