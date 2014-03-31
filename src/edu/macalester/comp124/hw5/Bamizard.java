package edu.macalester.comp124.hw5;

/**
 * Created by Benjamin Mathers on 3/30/14.
 */
public class Bamizard extends Enemy{

    public Bamizard(int speed, int health)
    {
        super("bamizard", speed, health, 5, 5);
        setName("Bamizard");
        defense = 10;
        imageFileName = "hw5RPG\\images\\monsters\\bamizard.png";
        AttackAction attack1 = new AttackAction(15,5,75,"Flamethrower");
        AttackAction attack2 = new AttackAction(25,5,60,"Fire Blast");
        AttackAction attack3 = new AttackAction(30,5,40,"Earthquake");
        combatActions.add(attack1);
        combatActions.add(attack2);
        combatActions.add(attack3);
    }
}
