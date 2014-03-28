package edu.macalester.comp124.hw5;

/**
 *
 * @author baylor
 */
public class Player extends Agent
{

    public Weapon equippedWeapon;
    public int attack;
    public AttackAction currentAttack;
    public Game theGame;

	public Player(int speed, int health)
	{
		super("player", speed, health);
        imageFileName = "hw5RPG\\images\\actor.png";
        Weapon dagger = new Weapon("dagger", "Dagger", 10);
        this.receiveItem(dagger);
        setEquippedWeapon(dagger);
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

    public String performCombatAction(Action action, Enemy opponent)
    {
       if (action instanceof AttackAction)
           return attackEnemy((AttackAction) action, opponent);
       if (action instanceof CombatItemAction)
           return useItem((CombatItemAction) action);
       return "";
    }

    public String attackEnemy(AttackAction action, Enemy target)
    {
        action.performAction(target);
        return (this.name + " attacked " + target.name + " with " + equippedWeapon.name + "!");
    }

    public String useItem(CombatItemAction action)
    {
        action.performAction(this);
        return (this.name + " used " + action.item.getName());
    }

    public String setEquippedWeapon(Weapon weapon)
    {
        equippedWeapon = weapon;
        currentAttack = new AttackAction(weapon.combatPower, 50, 100, weapon.getName());
        return (this.name + " equipped " + weapon.name + ".");
    }
}
