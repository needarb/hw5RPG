package edu.macalester.comp124.hw5;

/**
 *
 * @author baylor
 */
public class Player extends Agent
{
	public Player(int speed, int health)
	{
		super("player", speed, health);
	}

	@Override
	public void think()
	{
		//--- We don't think, the player thinks for us
	}
}
