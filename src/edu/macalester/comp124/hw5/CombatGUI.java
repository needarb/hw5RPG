package edu.macalester.comp124.hw5;
import javax.swing.*;
/**
 * Created by Benjamin Mathers on 3/26/14.
 */
public class CombatGUI
        extends JFrame
{

    private JPanel buttonPanel;

    public CombatGUI()//eventually will take a parameter of the monster, possibly the player and the monster
    {
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(500, 500);
        buttonPanel = new JPanel();
        buttonPanel.setVisible(true);
        this.add(buttonPanel);
        addBeginButtons();
    }

    public void addBeginButtons()
    {
        JButton attackButton = new JButton("Attack");
        JButton itemButton = new JButton("Use Item");
        JButton switchWeaponButton = new JButton("Switch Weapon");
        JButton runButton = new JButton("Run Away");

        buttonPanel.add(attackButton);
        buttonPanel.add(itemButton);
        buttonPanel.add(switchWeaponButton);
        buttonPanel.add(runButton);
    }

    public void addSwitchWeaponButtons()
    {
        buttonPanel.removeAll();
        /**
         * TODO
         * adds a button for each available weapon to switch to
         */
    }

    public void addItemButtons()
    {
        buttonPanel.removeAll();
        /**
         * TODO
         * add a button for every item that can be used
         */
    }

}
