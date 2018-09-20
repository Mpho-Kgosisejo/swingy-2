package mkgosisejo.models;

import mkgosisejo.factories.HeroFactory;
import mkgosisejo.providers.cache.Cache;

public class CreateHeroModel {
    public static String NAME_ERROR = "Name must be 5 Minimum and 10 Maximum in length";
    public static String HERO_EXISTS_ERROR = "Hero NAME already exists... try picking a different one";
    public static String ERROR_CREATING_HERO = "Error creating hero";

    public Hero newHero(String type, String name, String artifact){
        return (HeroFactory.NewHero(type, name, "0", "50", "25", "2500", artifact));
    }

    public boolean IsDuplicate(String heroName){
        for (Hero hero: Cache.Data.heroList) {
            if (hero.getName().equalsIgnoreCase(heroName))
                return (true);
        }
        return (false);
    }
}