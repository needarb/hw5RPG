package edu.macalester.comp124.hw5;

/**
 * Created by needa_000 on 3/28/14.
 */
public class Bamdoge extends Enemy
{
    public Bamdoge(int speed, int health)
    {
        super("bamdoge",speed,health,4,3);
        setName("Bamdoge");
        defense = 7;
        imageFileName = "hw5RPG\\images\\monsters\\bamdoge.png";
        AttackAction attack1 = new AttackAction(6,5,55,"Such Attack");
        AttackAction attack2 = new AttackAction(2,20,100,"Wow");
        AttackAction attack3 = new AttackAction(3,10,90,"Amaze");
        combatActions.add(attack1);
        combatActions.add(attack2);
        combatActions.add(attack3);
    }
}
