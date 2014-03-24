package edu.macalester.comp124.hw5;

/**
 * the actions in combat that the player and enemies can take
 */
public abstract class Action
{
    public int uses;
    public int accuracy;
    public String name;

    public Action(int uses, int accuracy, String name)
    {
        this.uses = uses;//if uses is -1, then there are unlimited uses.
        this.accuracy = accuracy;
        this.name = name;
    }

    //public abstract void performAction(Agent target);
}
