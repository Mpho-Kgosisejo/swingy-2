package mkgosisejo.models;

import mkgosisejo.factories.HeroFactory;

public class CreateHeroModel {
    public Hero newHero(String type, String name, String artifact){
        return (HeroFactory.NewHero(type, name, "0", "0", "0", "2500", artifact));
    }
}