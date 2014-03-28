package edu.macalester.comp124.hw5;

import acm.io.IOConsole;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author baylor
 */
public class Game
{
	public Map map;
	public Player player;	// change this to whatever subclass player is

	//--- A list of all the agents in the game (player, NPCs, monsters, etc.)
	//--- We need to know this so we know who to draw and so that we can ask
	//---	each turn what they want to do
	public List<Agent> agents = new LinkedList<>();
	public Game(Player player)
	{
		//--- Load a map
		map = new Map("home");

		//--- Create a player, stick him in the top left corner
        this.player = player;
        this.agents.add(player);

		//--- Add the player to the agents list. This list controls
	}

	public void movePlayer(int x, int y)
	{
		//--- Don't do anything if the move is illegal
        if(!map.isPassable(x,y))
           return;
		//--- Move the player to the new spot
		player.x = x;
		player.y = y;
        //---Resolve any items the player walked on
        if(map.getItem(x,y) != null)
            System.out.println("Player walked on an item");
             //player.giveItem(map.getItem(x,y));

        for(Agent a:agents)
        {
            if(player.x == a.x && player.y == a.y)
                if(a instanceof Enemy)
                {
                    Enemy e = (Enemy) a;
                    new Combat(player,e);
                    return;
                }
        }
        //---If possible teleport
        for(Teleporter t: map.getTeleporters())
        {
            System.out.println("Players x: " + x + " Players y: " + y);
            System.out.println("Teleporter x: " + t.getFromLocation().getX() + " Teleporter y: " + t.getFromLocation().getY());
            if(t.getFromLocation().getX() == x && t.getFromLocation().getY() == y)
            {
                teleportPlayer(t);
                return;
            }
        }
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

    public void teleportPlayer(Teleporter t)
    {
        System.out.println("Teleporting playing to new map: " + t.getNewMap());
        Map m = new Map(t.getNewMap());
        player.x = t.getToLocation().getX();
        player.y = t.getToLocation().getY();
        this.map = m;
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
