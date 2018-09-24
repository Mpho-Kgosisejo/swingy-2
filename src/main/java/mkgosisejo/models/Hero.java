package mkgosisejo.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import mkgosisejo.enums.Artifacts;
import mkgosisejo.utils.Formulas;

public class Hero {
    @Setter
    @Getter
    protected int id;

    @Setter
    @Getter
    @NotNull
    @NotBlank
    @Size(min = 5, max = 10)
    protected String name;

    @Setter
    @Getter
    @Min(0)
    protected int xp;

    @Setter
    @Getter
    @Min(50)
    protected int attack;

    @Setter
    @Getter
    @Min(25)
    protected int defence;

    @Setter
    @Getter
    @Min(0)
    protected int hp;

    @Setter
    @Getter
    @NotNull
    protected Artifacts artifact;

    @Setter
    @Getter
    protected Position position;

    public int getLevel(){
        return Formulas.GetLevel(this.xp);
    }

    public String getType(){
        return (this.getClass().getSimpleName().substring(4));
    }

    public boolean isAlive(){
        return (this.hp > 0);
    }
}