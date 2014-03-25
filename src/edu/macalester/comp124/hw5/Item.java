package edu.macalester.comp124.hw5;

/**
 * Created by needa_000 on 3/25/14.
 */
public abstract class Item
{
    String name;

    public Item(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
