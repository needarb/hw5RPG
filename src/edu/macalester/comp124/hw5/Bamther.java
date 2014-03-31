package edu.macalester.comp124.hw5;

/**
 * Created by Benjamin Mathers on 3/30/14.
 */
public class Bamther extends Enemy {

    public Bamther(int speed, int health)
    {
        super("bamthers", speed, health, 0, 0);
        setName("Bamthers");
        defense = 10;
        imageFileName = "hw5RPG\\images\\monsters\\bamther.png";

        AttackAction attack1 = new AttackAction(25,5,75,"BAM-SLAP");
        AttackAction attack2 = new AttackAction(30,5,65,"BAM-BITE");
        AttackAction attack3 = new AttackAction(50,3,30,"BAM-DONE");

        combatActions.add(attack1);
        combatActions.add(attack2);
        combatActions.add(attack3);
    }
}
