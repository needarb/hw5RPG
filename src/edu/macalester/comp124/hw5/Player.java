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

    public void performCombatAction(Action action, Agent opponent)
    {
       if (action instanceof AttackAction)
           attackEnemy((AttackAction) action, (Enemy) opponent);
       if (action instanceof CombatItemAction)
           useItem((CombatItemAction) action);
    }
    public String attackEnemy(AttackAction action, Enemy target)
    {
        action.performAction(target);
        return (this.name + "attacked " + target.name + " with " + equippedWeapon.name + "!");
    }

    public String useItem(CombatItemAction action)
    {
        action.performAction(this);
        return (this.name + " used " + action.item.getName());
    }

    public String switchWeapons(Weapon weapon) //TODO make a switchEquipableAction class
    {
        equippedWeapon = weapon;
        return (this.name + " equipped " + weapon.name + ".");
    }
}
