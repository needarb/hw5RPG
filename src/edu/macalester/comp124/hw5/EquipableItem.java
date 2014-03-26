package edu.macalester.comp124.hw5;

/**
 *
 * @author baylor
 */
public class EquipableItem extends Item
{
	public String id, description;
	public int strengthModifier, speedModifier, constitutionModifier;
	public int meleeAttackModifire, rangedAttackModifier, defenseModifier;

	public EquipableItem(String id, String description)
	{
        super("BANANA");
		this.id = id;
		this.description = description;
	}

	@Override
	public String toString()
	{
		return description;
	}
}
