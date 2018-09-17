package mkgosisejo.models;

import java.util.List;

import mkgosisejo.providers.DataProvider;
import mkgosisejo.providers.cache.Cache;

public class SelectHeroModel {
    private DataProvider _dataProvider = null;
    public static String STR_DELETION = "Deletion";
    public static String STR_DELETING = "Deleting";
    public static String STR_UPDATE = "Update";
    public static String STR_UPDATING = "Updating";

    public SelectHeroModel(){
        this._dataProvider = new DataProvider();
    }

    public Hero getSelectedHero(int index){
        return (index >= 0 && index < this.getHeroList().size()) ? this.getHeroList().get(index) : null;
    }

    public List<Hero> getHeroList(){
        return (Cache.Data.heroList);
    }    

    public boolean updateHero(Hero hero){
        return (this._dataProvider.updateHero(hero));
    }

    public boolean deleteHero(Hero hero){
        return (this._dataProvider.deleteHero(hero));
    }
}