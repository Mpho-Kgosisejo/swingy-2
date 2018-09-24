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
    public static String NO_EXISTING_HEROS = "There's no existing heros to select";

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

    public static String GetHeroDetails(Hero hero){
        String details = "";

        details += getHeroDetailFormat("Name", hero.getName()) + "\n";
        details += getHeroDetailFormat("Type", hero.getType()) + "\n";
        details += getHeroDetailFormat("Level", (hero.getLevel() + " (" + hero.getXp() + "XP)")) + "\n";
        details += getHeroDetailFormat("Artifact", hero.getArtifact().toString()) + "\n";
        details += getHeroDetailFormat("Attack", Integer.toString(hero.getAttack())) + "\n";
        details += getHeroDetailFormat("Defence", Integer.toString(hero.getDefence())) + "\n";
        details += getHeroDetailFormat("Hit Point", (hero.getHp() + "HP"));
        return (details);
    }

    private static String getHeroDetailFormat(String title, String text){
        return (String.format(" %9.9s | %s", title, text));
    }
}