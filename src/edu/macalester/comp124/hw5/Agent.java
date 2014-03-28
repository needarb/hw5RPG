package edu.macalester.comp124.hw5;
import javax.jws.soap.SOAPBinding;
import java.util.*;
/**
 * An agent is an entity that can move around. Examples: player, NPCs, monsters.
 * You can add code here if you need something or you can make subclasses
 * of this. What's important here is that there's an ID. This is used by
 * the rendering system to determine which picture to draw for it.
 * @author baylor
 */
public abstract class Agent
{
	public String type;	// tells us which picture to draw for this agent
    public int combatSpeed; //how fast the agent is in combat
    public int healthPoints;
    public int defense;
    public String name;
    public String imageFileName;

	public int x, y;
    public ArrayList<EquipableItem> equipableInventory;//weapons: swords and such
    public ArrayList<UseableItem> useableInventory;//useables: potions


	public  Agent(String type, int speed, int health)
	{
		this.type = type;
        combatSpeed = speed;
        healthPoints = health;
        equipableInventory = new ArrayList<>();
        useableInventory = new ArrayList<>();
    }

	/**
	 * The think() method is where agents decide what they're going to do.
	 * For this game, this normally means they decide where to move.
	 */
	 //public abstract void think();

    public void changeHealth(int change)//positive if it increases, negative if it decreases
    {
        healthPoints+=change;
    }

    public void receiveItem(Item item)
    {
        if(item instanceof UseableItem){
            UseableItem addItem = (UseableItem) item;
            useableInventory.add(addItem);
        }

        else
        {
            EquipableItem addItem = (EquipableItem) item;
            equipableInventory.add(addItem);
        }
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int takeDamage(int attack)
    {
        int damage = attack-this.defense;
        changeHealth(-damage);
        return damage;
    }

    public boolean isAlive()
    {
        if(this.healthPoints>0)
            return true;
        else
            return false;
    }
}
