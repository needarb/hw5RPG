package edu.macalester.comp124.hw5;

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
	public int x, y;

	public Agent(String type)
	{
		this.type = type;
	}

	/**
	 * The think() method is where agents decide what they're going to do.
	 * For this game, this normally means they decide where to move.
	 */
	 public abstract void think();
}
