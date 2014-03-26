package edu.macalester.comp124.hw5;

/**
 *
 * @author baylor
 */
public class EquipableItem extends Item
{
	public String id, name;
	public int strengthModifier, speedModifier, constitutionModifier;
	public int meleeAttackModifire, rangedAttackModifier, defenseModifier;

	public EquipableItem(String id, String name)
	{
        super(name);
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString()
	{
		return name;
	}
}
