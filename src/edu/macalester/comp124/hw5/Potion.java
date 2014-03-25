package edu.macalester.comp124.hw5;

/**
 * Created by Benjamin Mathers on 3/25/14.
 */
public class Potion
    extends UseableItem
{
    int potionPower;

    public Potion(String name, int uses, int power)
    {
        super(name, uses);
        potionPower = power;
    }

    public void useItem(Agent target)
    {
        if(uses>0)
        {
            target.changeHealth(potionPower);
            uses--;
        }
    }
}
