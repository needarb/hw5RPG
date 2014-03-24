package edu.macalester.comp124.hw5;

/**
 * Created by Benjamin Mathers on 3/24/14.
 */
public class AttackAction
        extends Action {

    public int strength;

    public AttackAction(int strength, int uses, int accuracy, String attackName)
    {
        super(uses, accuracy, attackName);
        this.strength = strength;
    }

    public void performAction(Agent target)
    {
        target.changeHealth(-strength);
    }
}
