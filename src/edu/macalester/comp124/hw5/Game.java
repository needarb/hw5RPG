package edu.macalester.comp124.hw5;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author baylor
 */
public class Game
{
	public Map map;
	public Agent player;	// change this to whatever subclass player is

	//--- A list of all the agents in the game (player, NPCs, monsters, etc.)
	//--- We need to know this so we know who to draw and so that we can ask
	//---	each turn what they want to do
	public List<Agent> agents = new LinkedList<>();

	public Game()
	{
		//--- Load a map
		map = new Map("main");

		//--- Create a player, stick him in the top left corner
		player = new Player();
		player.x = 2;
		player.y = 2;

		//--- Add the player to the agents list. This list controls
		agents.add(player);
	}

	public void movePlayer(int x, int y)
	{
		//--- Don't do anything if the move is illega

		//--- Move the player to the new spot
		player.x = x;
		player.y = y;

		//--- Assuming this is the last thing that happens in the round,
		//---	start a new round. This lets the other agents make their moves.
		nextTurn();
	}
	public void movePlayer(char direction)
	{
		switch(direction)
		{
			case 'n':
				movePlayer(player.x, player.y-1);
				break;
			case 's':
				movePlayer(player.x, player.y+1);
				break;
			case 'e':
				movePlayer(player.x+1, player.y);
				break;
			case 'w':
				movePlayer(player.x-1, player.y);
				break;
		}
	}

	/**
	 * Run a turn. Did the player run into an enemy? An item?
	 * What do the other agents (NPCs, monsters, etc.) want to do?
	 */
	private void nextTurn()
	{
		//--- Do whatever you do in a turn
	}

	private void onTouchMonster()
	{
		//--- Who did you run into?

		//--- Time to fight
//		CombatForm form = new CombatForm();
//		form.game = this;	// let them know about us so they can talk to us
//		form.enemies = ???;
//		form.run();
	}
}
