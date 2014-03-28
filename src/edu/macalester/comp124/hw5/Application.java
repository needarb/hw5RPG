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
        Player p = new Player(-1, -1);
        p.setName("Ned");
       
        Potion a = new Potion("Potion",1,20);
        Potion b = new Potion("Health", 1, 20);
        Weapon w = new Weapon("Sword", "Sword", 20);
        p.receiveItem(w);
        System.out.println(p.equipableInventory.toString());
        p.receiveItem(a);
        p.receiveItem(b);
        Enemy e = new BlackKnight("knight", 60, 30);
        e.setName("Knight");
        e.combatActions.add(new AttackAction(20,20,20, "attack"));
        Combat combat = new Combat(p, e);

        IOConsole console = new IOConsole();
		Game theGame = new Game(console);


        //--- Create Console Window
        JFrame consoleWindow = new JFrame("Console");
        consoleWindow.add(console);
        consoleWindow.setSize(500,500);

        CharacterCreationGUI characterCreation = new CharacterCreationGUI(theGame,p);

        // TODO: Load character screen, create/edit character


		//--- The map screen is a View of our game
		//--- It's also our Controller when navigating the map
		MainForm mapScreen = new MainForm(theGame);

		mapScreen.setVisible(true);
        consoleWindow.setVisible(true);
        console.println("Welcome to the The Bamther Chronicles!");
	}
}
