package edu.macalester.comp124.hw5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Map
{
	private String mapsDirectoryName = "hw5RPG/maps/";

    public List<Agent> mapAgents = new LinkedList<>();
	// Maps are represented (x,y) / (column, row) / (width, height)
	public String[][] terrain;	// grass, water, etc.
	public String[][] items;	// treasure, potions, etc.

	//--- Keep track of which types of terrain we can/cannot walk on
	//--- Technically, we're only tracking what we CAN'T walk on right now
	private HashMap<String,Boolean> passibility = new HashMap<>();

    //--- Teleporters on this map
    private Teleporter[] teleporters;
    //---Enemies on this map
    private Enemy[] enemies;
	//<editor-fold defaultstate="collapsed" desc="constructors and accessors">
	public Map(String mapName)
	{
        loadPassabilityInformation();
		loadMap(mapName);
        loadTeleporters(mapName);
        loadEnemies(mapName);
	}

    private void loadTeleporters(String mapName)
    {
        String fileName = mapName + ".terrain.map";
        teleporters = MapLoader.getTeleporters(mapsDirectoryName + fileName);
        for(Teleporter t: teleporters)
            System.out.println("Teleporter to " + t.getNewMap() + " at this location " + t.getFromLocation().getX() + "," + t.getFromLocation().getY());
    }

    private void loadEnemies(String mapName)
    {
        String fileName = mapName + ".terrain.map";
        enemies = MapLoader.getEnemies(mapsDirectoryName + fileName);
        for (Enemy e : enemies)
            mapAgents.add(e);
    }
    public Teleporter[] getTeleporters()
    {
        return teleporters;
    }

    public Enemy[] getEnemies() { return enemies; }

    private void loadPassabilityInformation()
	{
		String fileName  = "impassible terrain.txt";

		List<String> lines = DataLoader.loadLinesFromFile(mapsDirectoryName + fileName);
		for (int i = 0; i < lines.size(); i++)
		{
			String line = lines.get(i);
			line = line.trim();
			if (!line.isEmpty() && !line.startsWith("#"))
			{
				String[] tokens = line.split(",");
				String key = tokens[0].trim();
				passibility.put(key, false);
			}
		}
	}

	public void loadMap(String mapName)
	{
		String terrainMapName = mapName + ".terrain.map";
		terrain = MapLoader.getMap(mapsDirectoryName + terrainMapName);

		String itemMapName = mapName + ".items.map";
		items = MapLoader.getMap(mapsDirectoryName + itemMapName);

		//--- Just to be clean, convert any "." in the item map to nulls
		for (int x=0; x < getWidth(); x++)
		{
			for (int y=0; y < getHeight(); y++)
			{
				if (items[x][y].equals("."))
				{
					items[x][y] = null;
				}
			}
		}
	}

	public int getWidth()
	{
		return terrain.length;
	}
	public int getHeight()
	{
		return terrain[0].length;
	}
	//</editor-fold>


	//<editor-fold defaultstate="collapsed" desc="Object overrides">
	@Override
	public String toString()
	{
		//--- This is technically really, really inefficient but it's easy
		//---	so for now we're going to do this
		String output = "";

		//--- Important to loop through rows THEN columns
		//--- Otherwise it will draw sideways
		for (int y = 0; y < getHeight(); y++)
		{
			for (int x = 0; x < getWidth(); x++)
			{
				output += terrain[x][y];
			}
			output += System.lineSeparator();
		}
		return output;
	}

    public boolean isPassable(int x, int y)
    {
        if(x >= terrain.length || x < 0 || y >= terrain[x].length || y < 0)
            return false;
        if(!passibility.containsKey(terrain[x][y]))
            return true;
        return passibility.get(terrain[x][y]);
    }

    public Item getItem(int x, int y)
    {
        String itemString = items[x][y];
        if(itemString == null)
            return null;
        switch (itemString)
        {
            case("p"):          //New Potion
                return new Potion("Health Potion",1,10);
            case("S"):
                return Weapon.SWORD;
            case("I"):
                return Weapon.ICE_SWORD;
            case("F"):
                return Weapon.FIRE_SWORD;
            case("N"):
                return new Potion("Super-Mega-Awesome Health Potion",10,-15);
            default:
                return null;
        }
    }
    //</editor-fold>
}
