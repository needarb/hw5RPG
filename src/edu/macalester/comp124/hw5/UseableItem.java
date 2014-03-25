package edu.macalester.comp124.hw5;

/**
 * Created by Benjamin Mathers on 3/25/14.
 */
public abstract class UseableItem
    extends Item
{

    int uses;

    public UseableItem(String name, int uses)
    {
        super(name);
        this.uses = uses;
    }

    public abstract void useItem(Agent target);

    public int getUsesLeft()
    {
        return uses;
    }
}
