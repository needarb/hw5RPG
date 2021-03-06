package edu.macalester.comp124.hw5;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

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
    private boolean inCombat;
    private ArrayList<StatsGUI> statGUIS;

    public Player(int speed, int health)
    {
        super("player", speed, health);
        inCombat = false;
        statGUIS = new ArrayList<>();
        imageFileName = "hw5RPG\\images\\actor.png";
        Weapon dagger = Weapon.DAGGER;
        this.receiveItem(dagger);
        setEquippedWeapon(dagger);
    }

    public void setInCombat(boolean combat) { this.inCombat = combat; }

    public void setHealth(int health)
    {
        this.healthPoints = health;
        updateGUIS();
    }

    public void setAttack(int attack)
    {
        this.attack = attack;
        updateGUIS();
    }

    public void setDefense(int defense)
    {
        this.defense = defense;
        updateGUIS();
    }

    public void setSpeed(int speed)
    {
        this.combatSpeed = speed;
        updateGUIS();
    }

    public void addStatsGUI(StatsGUI e)
    {
        statGUIS.add(e);
    }

    public void updateGUIS()
    {
        for(StatsGUI e:statGUIS)
            e.updateStats();
    }
    public void updateUseableInventory()
    {
        for(UseableItem item : useableInventory)
        {
            if (item.getUsesLeft() <= 0)
            {
                useableInventory.remove(item);
                return;
            }
        }
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

    public boolean isInCombat() {return inCombat;}
}
