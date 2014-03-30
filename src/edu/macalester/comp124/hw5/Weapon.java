package edu.macalester.comp124.hw5;

/**
 * Created by Benjamin Mathers on 3/26/14.
 */
public class Weapon
    extends EquipableItem
{
    public static final Weapon SWORD = new Weapon("sword","Sword",20);
    public static final Weapon DAGGER = new Weapon("dagger","Dagger",10);
    int combatPower;

    public Weapon(String id, String name, int combatPower)
    {
        super(id, name);
        this.combatPower = combatPower;
    }
}
