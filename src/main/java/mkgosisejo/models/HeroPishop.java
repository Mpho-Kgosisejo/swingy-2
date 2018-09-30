package mkgosisejo.models;

import mkgosisejo.enums.Artifacts;
import mkgosisejo.interfaces.IHero;

public class HeroPishop extends Hero implements IHero {
    public int specialPower() {
        return 0;
    }

    @Override
    public int getAttack() {
        int boost = 0;

        if (this.artifact == Artifacts.WEAPON){
            boost = 25;
        }
        return (super.getAttack() + boost);
    }

    @Override
    public int getDefence() {
        int boost = 0;

        if (this.artifact == Artifacts.ARMOR){
            boost = 30;
        }
        return (super.getDefence() + boost);
    }

    @Override
    public int getHp() {
        int boost = 0;

        if (this.artifact == Artifacts.HELM){
            boost = 15;
        }
        return (super.getHp() + boost);
    }
}