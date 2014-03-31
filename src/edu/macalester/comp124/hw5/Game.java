package edu.macalester.comp124.hw5;

import acm.io.IOConsole;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.HashMap;
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
    public MainForm mainForm;
    public static final String[] MAP_NAMES = {"main","home","mountains","forest","lava1"};
    private HashMap<String,Map> maps;
	public Game(Player player)
	{
		//--- Load da maps
        maps = new HashMap<>();
		for(String s:MAP_NAMES)
            maps.put(s,new Map(s));
        this.map = maps.get("home");

		//--- Create a player, stick him in the top left corner
        this.player = player;
        this.player.theGame = this;

        updateAgents();

		//--- Add the player to the agents list. This list controls
	}

    private void updateAgents()
    {
            agents = this.map.mapAgents;

        if (!agents.contains(player))
            agents.add(player);
    }

    public void moveEnemy()
    {
        int x, y; //enemy x and y
        for(Enemy e : map.getEnemies())
        {
            int[] coordinates = e.moveEnemy();
            x = coordinates[0];
            y = coordinates[1];

            if(!map.isPassable(x,y))
            {
                e.switchMovingForward();
                return;
            }

            if(player.isInCombat())
                return;

            //makes sure enemy doesn't move through the player
            if(x == player.x && y == player.y)
                return;

            e.x = x;
            e.y = y;
        }
    }

	public void movePlayer(int x, int y)
	{
        //--moves the enemy first
        moveEnemy();

		//--- Don't do anything if the move is illegal
        if(!map.isPassable(x,y))
           return;

        //--- Don't do anything if the player is fighting an enemy
        if(player.isInCombat())
            return;

        //--- If in a fight, fight
        for(Agent a:agents)
        {
            if(x == a.x && y == a.y)
                if(a instanceof Enemy)
                {
                    Enemy e = (Enemy) a;
                    new Combat(player,e);
                    return;
                }
        }

		//--- Move the player to the new spot
		player.x = x;
		player.y = y;

        //---Resolve any items the player walked on
        if(map.getItem(x,y) != null)
        {
             player.receiveItem(map.getItem(x, y));
            map.items[x][y] = null;
        }


        //--- If possible teleport
        for(Teleporter t: map.getTeleporters())
        {
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
        player.x = t.getToLocation().getX();
        player.y = t.getToLocation().getY();
        this.map = maps.get(t.getNewMap());

        updateAgents();
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
