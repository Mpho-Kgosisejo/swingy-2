package mkgosisejo.factories;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import mkgosisejo.enums.Artifacts;
import mkgosisejo.models.Hero;
import mkgosisejo.models.HeroKing;
import mkgosisejo.models.HeroKnight;
import mkgosisejo.models.HeroPawn;
import mkgosisejo.models.HeroPishop;
import mkgosisejo.models.HeroQueen;
import mkgosisejo.models.HeroRook;

public class HeroFactory {
    private static String NewHeroStatus = "";
    private static Validator validator;
    private static int _id = 1;

    public static Hero NewHero(String type, String name, String xp, String attack, String defence, String hp, String artifact){
        try {
            Hero hero = null;
            int nXP = Integer.parseInt(xp);
            int nAttack = Integer.parseInt(attack);
            int nDefence = Integer.parseInt(defence);
            int nHP = Integer.parseInt(hp);
            Artifacts eArtifact = Artifacts.valueOf(artifact.toUpperCase());
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            validator = factory.getValidator();

            if (type.equalsIgnoreCase("king"))
                hero = new HeroKing();
            else if (type.equalsIgnoreCase("knight"))
                hero = new HeroKnight();
            else if (type.equalsIgnoreCase("pawn"))
                hero = new HeroPawn();
            else if (type.equalsIgnoreCase("pishop"))
                hero = new HeroPishop();
            else if (type.equalsIgnoreCase("queen"))
                hero = new HeroQueen();
            else if (type.equalsIgnoreCase("rook"))
                hero = new HeroRook();
            else{
                NewHeroStatus = "Hero type \"" + type + "\" does not exists";
                return (null);
            }

            hero.setId(_id);
            hero.setName(name.trim());
            hero.setXp(nXP);
            hero.setAttack(nAttack);
            hero.setDefence(nDefence);
            hero.setHp(nHP);
            hero.setArtifact(eArtifact);

            Set<ConstraintViolation<Hero>> constraintViolations = validator.validate(hero);
            for (Iterator<ConstraintViolation<Hero>> constraintViolationsMssg = constraintViolations.iterator(); constraintViolationsMssg.hasNext();){
                NewHeroStatus += constraintViolationsMssg.next().getMessage() + "\n";
            }
            if (constraintViolations.size() == 0){
                NewHeroStatus = "Hero successfully created";
                _id++;
                return (hero);
            }
        } catch (Exception e) {
            NewHeroStatus = "Something went wrong: " + e.getMessage();
        }
        return (null);
    }

    public static String GetNewHeroStatus(){
        String status = NewHeroStatus;
        NewHeroStatus = "";
        return (status);
    }
}