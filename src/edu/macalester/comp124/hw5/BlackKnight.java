package edu.macalester.comp124.hw5;

/**
 * Created by Benjamin Mathers on 3/27/14.
 */
public class BlackKnight extends Enemy {

    public BlackKnight(String type, int speed, int health)
    {
        super("blackKnight", speed, health, 5, 5);
        setName("Black Knight");
        defense = 4;
        imageFileName = "hw5RPG\\images\\monsters\\blackKnight.png";
        AttackAction attack1 = new AttackAction(5,5,55,"Skull Bash");
        AttackAction attack2 = new AttackAction(1,20,100,"Kick");
        AttackAction attack3 = new AttackAction(2,10,90,"Slash");
        combatActions.add(attack1);
        combatActions.add(attack2);
        combatActions.add(attack3);
    }


}
