package edu.macalester.comp124.hw5;

/**
 *
 * @author baylor
 */
public class Application
{
	public static void main(String[] args)
	{
		//--- The game is where all the interesting stuff happens
		//--- Formally, it's called the Model
		//--- The thing that draws the picture is called the View
		//--- The thing that lets players select actions is the Controller
        Player player = new Player(-1, -1);
        player.x = 7;
        player.y = 16;
        player.setName("Ulric Fishke");
        System.out.println(player.equipableInventory.toString());

        BeginScreen begin = new BeginScreen();

		Game theGame = new Game(player);

        CharacterCreationGUI characterCreation = new CharacterCreationGUI(theGame,player);

        // TODO: Load character screen, create/edit character


		//--- The map screen is a View of our game
		//--- It's also our Controller when navigating the map
		MainForm mapScreen = new MainForm(theGame);
		mapScreen.setVisible(true);
	}
}
