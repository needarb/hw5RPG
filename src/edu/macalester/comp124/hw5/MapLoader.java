package edu.macalester.comp124.hw5;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author baylor
 */
public class MapLoader
{
    public static final char TELE_STARTER = '%';
    public static final char ENEMY_STARTER = '@';

    public static String[][] getMap(String fqn)
	{
		int width, height;
		String[][] map;

		List<String> lines = DataLoader.loadLinesFromFile(fqn);
		String[] tokens = lines.get(0).split(" ");

		width  = tokens.length;
		height = lines.size();
        if(lines.get(lines.size()-1).charAt(0) == TELE_STARTER)
            height--;
        else if(lines.get(lines.size()-1).charAt(0) == ENEMY_STARTER)
            height -= 2;
		map = new String[width][height];

		for (int y = 0; y < height; y++)
		{
			String line = lines.get(y);
			tokens = line.split(" ");
			for (int x = 0; x < width; x++)
			{
				map[x][y] = tokens[x];
			}
		}
		return map;
	}

    public static final String TELEPORTER_CODES_SPLIT = "_";       //--- What splits each code section (see below) ie 1,2>>lavaarea>>5,7_4,5>>waterarea>>7,8
    public static final String TELEPORTER_CODE_PARTS_SPLIT = ">>"; //---from location(ie on old map)>>new map name>>to location(ie on old map) [x],[y]>>[mapName]>>[x],[y]
    public static Teleporter[] getTeleporters(String fqn)
    {
        //Map File
        List<String> lines = DataLoader.loadLinesFromFile(fqn);
        //Last line (which has teleporter code)
        String teleportersLine = lines.get(findSpecCharacter(lines, TELE_STARTER));
        //Split up the individual teleporters. First remove all spaces and dashes
        String[] teleportersStrings =  (teleportersLine.substring(1).replaceAll("[(){} \\[\\]\\-]","").split(TELEPORTER_CODES_SPLIT));
        //Create Teleporter Array
        Teleporter[] teleporters = new Teleporter[teleportersStrings.length];
        //Load each teleporter into the array
        for (int i = 0; i < teleportersStrings.length; i++)
        {
            //Take the code for one teleporter and split into its source location, new map name, and end location
            String teleString = teleportersStrings[i];
            String[] parts = teleString.split(TELEPORTER_CODE_PARTS_SPLIT);
            String newMap = parts[1];                                                                                                 //New map name
            Location fromLocation = new Location(Integer.parseInt(parts[0].split(",")[0]),Integer.parseInt(parts[0].split(",")[1]));  //Character Location on old map to trigger teleport
            Location toLocation = new Location(Integer.parseInt(parts[2].split(",")[0]),Integer.parseInt(parts[2].split(",")[1]));    //Character Location on new map
            teleporters[i] = new Teleporter(newMap,fromLocation,toLocation);
        }
        return teleporters;
    }

    public static final String ENEMY_CODE_SPLITS = "_";
    public static final String ENEMY_INFO_SPLITS = ":";
    public static Enemy[] getEnemies(String fqn)
    {
        Enemy[] enemies = new Enemy[0];
        HashMap<String, Enemy> enemyCode = new HashMap<>();
        enemyCode.put("b", new BlackKnight(20,30));
        enemyCode.put("d", new Bamdoge(10,30));
        enemyCode.put("r", new Bamrat(20,10));
        //Map file
        List<String> lines = DataLoader.loadLinesFromFile(fqn);
        //line with enemy code
        if (findSpecCharacter(lines, ENEMY_STARTER) != -1)
        {
            String enemyLine = lines.get(findSpecCharacter(lines,  ENEMY_STARTER));
            //split up different enemies and coordinates
            String[] enemyStrings = (enemyLine.substring(1).split(ENEMY_CODE_SPLITS));

            enemies = new Enemy[enemyStrings.length];

            for (int i = 0; i < enemyStrings.length; i++)
            {
                String enemyInfo = enemyStrings[i];
                String[] info = enemyInfo.split(ENEMY_INFO_SPLITS);

                String enemyType = info[0];//indicator of which enemy

                String[] enemyLoc = info[1].replaceAll("[()]","").split(",");//the coordinates of the enemy
                int xLoc = Integer.parseInt(enemyLoc[0]);
                int yLoc = Integer.parseInt(enemyLoc[1]);
                //The set path for the enemy
                String[] pathPoints = info[2].replaceAll("[\\[|\\]]", "").split(">>");
                String[] pathPointAString = pathPoints[0].split(",");
                String[] pathPointBString = pathPoints[1].split(",");

                int[] pathPointA = new int[2];
                int[] pathPointB = new int[2];
                for (int j = 0; j < pathPointAString.length; j++)
                {
                    pathPointA[j] = Integer.parseInt(pathPointAString[j]);
                    pathPointB[j] = Integer.parseInt(pathPointBString[j]);
                }

                Enemy e = createEnemy(enemyType);
                e.x = xLoc;
                e.y = yLoc;
                e.setPath(pathPointA, pathPointB);

                enemies[i] = e;
            }
        }

        return enemies;
    }

    private static Enemy createEnemy(String c)
    {
        switch(c)
        {
            case "b":
                return (new BlackKnight(20,30));
            case "d":
                return (new Bamdoge(10,30));
            case "r":
                return (new Bamrat(25,10));
            default:
                return null;
        }
    }

    private static int findSpecCharacter(List<String> lines, char specChar)
    {
        for(int i = 0; i < lines.size(); i++)
        {
            if(lines.get(i).charAt(0) == specChar)
                return i;
        }

        return -1;
    }
}
