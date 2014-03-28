package edu.macalester.comp124.hw5.music;

import edu.macalester.comp124.hw5.AttackAction;
import edu.macalester.comp124.hw5.Enemy;

/**
 * Created by needa_000 on 3/27/14.
 */
public class BamRat extends Enemy
{
    public BamRat(int speed, int health)
    {
        super("bamrat",speed,health,2,2);
        AttackAction attack1 = new AttackAction(1,20,95,"Scratch");
        AttackAction attack2 = new AttackAction(2,4,85,"Bite");
        combatActions.add(attack1);
        combatActions.add(attack2);
        imageFileName = "/images/monsters/bamrat.png";
    }
}
