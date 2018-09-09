package mkgosisejo.interfaces;

import java.util.List;

import mkgosisejo.models.Hero;

public interface IDataProvider {
    public boolean insertHero();
    public List<Hero> getHeros();
    public boolean updateHero();
    public boolean deleteHero();
}