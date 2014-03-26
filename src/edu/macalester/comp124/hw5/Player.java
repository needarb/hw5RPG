package edu.macalester.comp124.hw5;

/**
 *
 * @author baylor
 */
public class Player extends Agent
{

    public Weapon equippedWeapon;
    public int attack;

	public Player(int speed, int health)
	{
		super("player", speed, health);
	}

	@Override
	public void think()
	{
		//--- We don't think, the player thinks for us
	}

    public String attackEnemy(Enemy target)
    {
        int attack = equippedWeapon.combatPower + this.attack;
        int damage = target.takeDamage(attack);
        return (this.name + "attacked " + target.name + " with " + equippedWeapon.name + "! It caused " + damage + " damage");
    }

    public String useItem(UseableItem item)
    {
        item.useItem(this);
        return (this.name + " used " + item.getName());
    }

    public String switchWeapons(Weapon weapon)
    {
        equippedWeapon = weapon;
        return (this.name + " equipped " + weapon.name + ".");
    }
}
