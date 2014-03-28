package edu.macalester.comp124.hw5;

/**
 *
 * @author baylor
 */
public class Player extends Agent
{

    public Weapon equippedWeapon;
    public int attack;
    public AttackAction currentAttack;;

	public Player(int speed, int health)
	{
		super("player", speed, health);
        imageFileName = "hw5RPG\\images\\actor.png";
	}

    public void setHealth(int health)
    {
        this.healthPoints = health;
    }
    public void setAttack(int attack)
    {
        this.attack = attack;
    }
    public void setDefense(int defense)
    {
        this.defense = defense;
    }
    public void setSpeed(int speed)
    {
        this.combatSpeed = speed;
    }



	//public void think(){}

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

    public String setEquippedWeapon(Weapon weapon) //TODO make a switchEquipableAction class
    {
        equippedWeapon = weapon;
        currentAttack = new AttackAction(weapon.combatPower, 50, 100, weapon.getName());
        return (this.name + " equipped " + weapon.name + ".");
    }
}
