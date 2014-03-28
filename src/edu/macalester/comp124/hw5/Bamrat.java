package edu.macalester.comp124.hw5;

/**
 * Created by needa_000 on 3/28/14.
 */
public class Bamrat extends Enemy
{
    public Bamrat(int speed, int health)
    {
        super("bamrat",speed,health,3,3);
        setName("Bamrat");
        defense = 2;
        imageFileName = "hw5RPG\\images\\monsters\\bamrat.png";
        AttackAction attack1 = new AttackAction(3,5,55,"Bite");
        AttackAction attack2 = new AttackAction(1,20,100,"Scratch");
        AttackAction attack3 = new AttackAction(0,10,90,"Fly");
        combatActions.add(attack1);
        combatActions.add(attack2);
        combatActions.add(attack3);
    }
}
