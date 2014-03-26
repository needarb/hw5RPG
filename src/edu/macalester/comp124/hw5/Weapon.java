package edu.macalester.comp124.hw5;

/**
 * Created by Benjamin Mathers on 3/26/14.
 */
public class Weapon
    extends EquipableItem
{

    int combatPower;

    public Weapon(String id, String name, int combatPower)
    {
        super(id, name);
        this.combatPower = combatPower;
    }
}
