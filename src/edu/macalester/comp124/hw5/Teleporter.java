package edu.macalester.comp124.hw5;

/**
 * Created by needa_000 on 3/25/14.
 */
public class Teleporter
{
    private String newMap;
    private Location fromLocation;
    private Location toLocation;
    public Teleporter(String newMap,Location fromLocation, Location toLocation)
    {
        this.newMap = newMap;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
    }

    public String getNewMap()
    {
        return newMap;
    }

    public Location getFromLocation()
    {
        return fromLocation;
    }

    public Location getToLocation()
    {
        return toLocation;
    }
}
