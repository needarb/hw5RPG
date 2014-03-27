package edu.macalester.comp124.hw5;

/**
 * Created by Benjamin Mathers on 3/25/14.
 */
public abstract class UseableItem
    extends Item
{

    int uses;
    public CombatItemAction combatUse;

    public UseableItem(String name, int uses)
    {
        super(name);
        this.uses = uses;
        combatUse = new CombatItemAction(uses, 100, name, this);
    }

    public abstract void useItem(Agent target);

    public int getUsesLeft()
    {
        return uses;
    }
}
