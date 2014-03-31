package edu.macalester.comp124.hw5;

/**
 * Created by Benjamin Mathers on 3/30/14.
 */
public class Bambat extends Enemy {

    public Bambat(int speed, int health)
    {
        super("bambat", speed, health, 5, 5);
        setName("Bambat");
        defense = 10;
        imageFileName = "hw5RPG\\images\\monsters\\bambat.png";
        AttackAction attack1 = new AttackAction(3,5,80,"Tackle");
        AttackAction attack2 = new AttackAction(4,5,90,"Rabies Bite");
        AttackAction attack3 = new AttackAction(5,5,85,"Vampiric Stare");

        combatActions.add(attack1);
        combatActions.add(attack2);
        combatActions.add(attack3);
    }
}
