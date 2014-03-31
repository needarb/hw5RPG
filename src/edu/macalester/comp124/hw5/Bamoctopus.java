package edu.macalester.comp124.hw5;

/**
 * Created by Benjamin Mathers on 3/30/14.
 */
public class Bamoctopus extends Enemy{

    public Bamoctopus(int speed, int health)
    {
        super("bamoctopus", speed, health, 7, 7);
        setName("Bamoctopus");
        defense = 10;
        imageFileName = "hw5RPG\\images\\monsters\\bamoctopus.png";
        AttackAction attack1 = new AttackAction(15,5,75,"8-Arm Punch");
        AttackAction attack2 = new AttackAction(25,5,60,"Freaky Octo-Beaky");
        AttackAction attack3 = new AttackAction(30,5,40,"Tsunami");

        combatActions.add(attack1);
        combatActions.add(attack2);
        combatActions.add(attack3);
    }
}
