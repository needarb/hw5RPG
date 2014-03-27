package edu.macalester.comp124.hw5;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Benjamin Mathers on 3/26/14.
 */
public class CombatGUI
        extends JFrame
        implements ActionListener
{

    private JPanel buttonPanel;
    private JButton attackButton;
    private JButton itemButton;
    private JButton switchWeaponButton;
    private JButton runButton;

    HashMap<JButton, UseableItem> useableItemButtons;

    Combat combat;

    public CombatGUI(Combat combat)//eventually will take a parameter of the monster, possibly the player and the monster
    {
        this.combat = combat; //the combat passes itself as  a parameter to allow for the GUI to influence it
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(500, 500);
        buttonPanel = new JPanel();
        buttonPanel.setVisible(true);
        this.add(buttonPanel);
        addBeginButtons();

        useableItemButtons = new HashMap<>();
    }

    public void addBeginButtons()
    {
        attackButton = new JButton("Attack");
        itemButton = new JButton("Use Item");
        switchWeaponButton = new JButton("Switch Weapon");
        runButton = new JButton("Run Away");

        attackButton.addActionListener(this);
        itemButton.addActionListener(this);
        switchWeaponButton.addActionListener(this);
        runButton.addActionListener(this);

        buttonPanel.add(attackButton);
        buttonPanel.add(itemButton);
        buttonPanel.add(switchWeaponButton);
        buttonPanel.add(runButton);
    }

    public void addSwitchWeaponButtons()
    {
        buttonPanel.setVisible(false);
        buttonPanel.removeAll();
        /**
         * TODO
         * adds a button for each available weapon to switch to
         */
    }

    public void addItemButtons()
    {
        buttonPanel.setVisible(false);
        buttonPanel.removeAll();

        for(UseableItem item: combat.player.useableInventory)
        {
            JButton button = new JButton(item.getName());
            buttonPanel.add(button);
            button.setVisible(true);
            useableItemButtons.put(button, item);
            button.addActionListener(this);
        }

        buttonPanel.setVisible(true);

        /**
         * add a button for every item that can be used
         */
     }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == attackButton)
            combat.takeTurn(combat.player.currentAttack); //the player decides to attack
        if (e.getSource() == itemButton)
            addItemButtons();//Allows character to choose which item to use

        if (useableItemButtons.containsKey(e.getSource()))
            combat.takeTurn(useableItemButtons.get(e.getSource()).combatUse);//uses the item the player chooses

        if (e.getSource() == switchWeaponButton)
            addSwitchWeaponButtons();
        if (e.getSource() == runButton){}

    }
}
