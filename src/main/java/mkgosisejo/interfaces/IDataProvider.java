package mkgosisejo.interfaces;

import java.util.List;

import mkgosisejo.models.Hero;

public interface IDataProvider {
    public boolean insertHero(Hero hero);
    public List<Hero> getHeros();
    public boolean updateHero(Hero hero);
    public boolean deleteHero(Hero hero);
}