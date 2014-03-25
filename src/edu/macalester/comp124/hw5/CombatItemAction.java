package edu.macalester.comp124.hw5;

/**
 * Created by Benjamin Mathers on 3/24/14. Uses UseableItems in combat
 */
public class CombatItemAction extends Action
{

    UseableItem item;
    public CombatItemAction(int uses, int accuracy, String name, UseableItem item)
    {
        super(uses, accuracy, name);
        this.item = item;
    }

    public void performAction(Agent target)
    {
        item.useItem(target);
    }
}
