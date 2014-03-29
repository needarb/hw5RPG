package edu.macalester.comp124.hw5;

import acm.io.IOConsole;

import javax.swing.*;
import java.awt.*;
import java.io.Console;

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
       
        Potion a = new Potion("Potion",1,20);
        Potion b = new Potion("Health", 1, 20);
        Weapon w = new Weapon("Sword", "Sword", 20);
        player.receiveItem(w);
        System.out.println(player.equipableInventory.toString());
        player.receiveItem(a);
        player.receiveItem(b);
        Enemy e = new BlackKnight("knight", 5, 30);
        e.x = 9;
        e.y = 6;
        Enemy e2 = new BlackKnight("knight",15,15);
        e2.x=10;
        e2.y=6;
        Enemy e3 = new Bamrat(6,10);
        e3.x = 3;
        e3.y = 4;
        Enemy e4 = new Bamdoge(1,25);
        e4.x = 2;
        e4.y = 18;


        //Combat combat = new Combat(p, e);

		Game theGame = new Game(player);
        theGame.agents.add(e);
        theGame.agents.add(e2);
        theGame.agents.add(e3);
        theGame.agents.add(e4);
        CharacterCreationGUI characterCreation = new CharacterCreationGUI(theGame,player);

        // TODO: Load character screen, create/edit character


		//--- The map screen is a View of our game
		//--- It's also our Controller when navigating the map
		MainForm mapScreen = new MainForm(theGame);
		mapScreen.setVisible(true);
	}
}
