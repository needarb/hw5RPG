package edu.macalester.comp124.hw5;

import java.util.List;

/**
 *
 * @author baylor
 */
public class MapLoader
{
    public static final char TELE_STARTER = '%';
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

    public static final String TELEPORTER_CODES_SPLIT = "_";       //--- What splits each code section (see below) ie 1,2>>lavaarea>>5,7||4,5>>waterarea>>7,8
    public static final String TELEPORTER_CODE_PARTS_SPLIT = ">>"; //---from location(ie on old map)>>new map name>>to location(ie on old map) [x],[y]>>[mapName]>>[x],[y]
    public static Teleporter[] getTeleporters(String fqn)
    {
        List<String> lines = DataLoader.loadLinesFromFile(fqn);
        String teleportersLine = lines.get(lines.size()-1);
        String[] teleportersStrings =  (teleportersLine.substring(1).replace(" ","").split(TELEPORTER_CODES_SPLIT));
        System.out.println("Number of teleporters: " + teleportersStrings.length);
        Teleporter[] teleporters = new Teleporter[teleportersStrings.length];
        for (int i = 0; i < teleportersStrings.length; i++)
        {
            String teleString = teleportersStrings[i];
            String[] parts = teleString.split(TELEPORTER_CODE_PARTS_SPLIT);
            String newMap = parts[1];
            Location fromLocation = new Location(Integer.parseInt(parts[0].split(",")[0]),Integer.parseInt(parts[0].split(",")[1]));
            Location toLocation = new Location(Integer.parseInt(parts[2].split(",")[0]),Integer.parseInt(parts[2].split(",")[1]));
            teleporters[i] = new Teleporter(newMap,fromLocation,toLocation);
        }
        return teleporters;
    }
}
