package edu.macalester.comp124.hw5;
import javax.swing.*;
import java.awt.*;
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
    private JPanel playerPanel;
    private JPanel opponentPanel;
    private JPanel buttonPanel;
    private JPanel feedbackPanel;
    private JPanel statsPanel; //holds all info that is not buttons of feedback

    private JButton attackButton;
    private JButton itemButton;
    private JButton switchWeaponButton;
    private JButton runButton;

    private JLabel playerImage;
    private JLabel opponentImage;
    private JLabel playerName;
    private JLabel playerHealth;
    private JLabel opponentName;
    private JLabel opponentHealth;

    private JTextField feedbackField;

    HashMap<JButton, UseableItem> useableItemButtons;
    HashMap<JButton, Weapon> weaponButtons;

    Combat combat;

    public CombatGUI(Combat combat)//eventually will take a parameter of the monster, possibly the player and the monster
    {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.combat = combat; //the combat passes itself as  a parameter to allow for the GUI to influence it
        useableItemButtons = new HashMap<>();
        weaponButtons = new HashMap<>();

        this.setVisible(true);
        this.setResizable(true);
        this.setSize(500, 500);

        playerPanel = new JPanel();
        opponentPanel = new JPanel();
        buttonPanel = new JPanel();
        feedbackPanel = new JPanel();
        statsPanel = new JPanel();

        playerPanel.setVisible(true);
        opponentPanel.setVisible(true);
        feedbackPanel.setVisible(true);
        buttonPanel.setVisible(true);
        statsPanel.setVisible(true);

        playerPanel.setSize(250, 350);
        opponentPanel.setSize(250,350);
        buttonPanel.setSize(500, 100);
        feedbackPanel.setSize(500, 50);
        statsPanel.setSize(500, 350);

        playerImage = new JLabel(new ImageIcon(combat.player.imageFileName));
        opponentImage = new JLabel(new ImageIcon(combat.opponent.imageFileName));
        playerName = new JLabel(combat.player.name);
        playerHealth = new JLabel("Health: " + combat.player.healthPoints);
        opponentName = new JLabel(combat.opponent.name);
        opponentHealth = new JLabel ("Health: " + combat.opponent.healthPoints);

       playerImage.setVisible(true);
        opponentImage.setVisible(true);
        playerName.setVisible(true);
        playerHealth.setVisible(true);
        opponentName.setVisible(true);
        opponentHealth.setVisible(true);


        playerImage.setSize(250, 250);
        opponentImage.setSize(250,250);
        playerName.setSize(250,50);
        playerHealth.setSize(250,50);
        opponentName.setSize(250,50);
        opponentHealth.setSize(250,50);

        playerPanel.add(playerImage);
        playerPanel.add(playerName);
        playerPanel.add(playerHealth);

        opponentPanel.add(opponentImage);
        opponentPanel.add(opponentName);
        opponentPanel.add(opponentHealth);

        feedbackField = new JTextField(combat.player.name + " is fighting " + combat.opponent.name + "!!!");
        feedbackField.setVisible(true);
        feedbackField.setSize(500,50);
        feedbackField.setEditable(false);

        feedbackPanel.add(feedbackField);

        statsPanel.add(playerPanel);
        statsPanel.add(opponentPanel);

        setFrameLayout();

        this.add(statsPanel);
        this.add(buttonPanel);
        this.add(feedbackPanel);

        System.out.println(playerImage.getLocation());
        addBeginButtons();

    }

    private void setFrameLayout()
    {
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        opponentPanel.setLayout(new BoxLayout(opponentPanel, BoxLayout.Y_AXIS));
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
    }
    /**private void setFrameLayout()
    {
        //playerLayout
        GroupLayout playerLayout = new GroupLayout(playerPanel);
        playerPanel.setLayout(playerLayout);

        playerLayout.setHorizontalGroup(
            playerLayout.createSequentialGroup()
                  .addGroup(playerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(playerImage)
                        .addComponent(playerName)
                        .addComponent(playerHealth))
        );
        playerLayout.setVerticalGroup(
                playerLayout.createSequentialGroup()
                        .addComponent(playerImage)
                        .addComponent(playerName)
                        .addComponent(playerHealth)
        );

        //opponentLayout
        GroupLayout oppLayout = new GroupLayout(opponentPanel);
        opponentPanel.setLayout(oppLayout);

        oppLayout.setHorizontalGroup(
                oppLayout.createSequentialGroup()
                        .addGroup(oppLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(opponentImage)
                                .addComponent(opponentName)
                                .addComponent(opponentHealth))
        );
        oppLayout.setVerticalGroup(
                oppLayout.createSequentialGroup()
                        .addComponent(opponentImage)
                        .addComponent(opponentName)
                        .addComponent(opponentHealth)
        );

        //statsLayout
        GroupLayout statsLayout = new GroupLayout(statsPanel);
        statsPanel.setLayout(statsLayout);

        statsLayout.setHorizontalGroup(
                statsLayout.createSequentialGroup()
                .addComponent(playerPanel)
                .addComponent(opponentPanel)
        );
        statsLayout.setVerticalGroup(
                statsLayout.createSequentialGroup()
                .addGroup(statsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(playerPanel)
                .addComponent(opponentPanel))
        );

        //buttonLayout
        buttonPanel.setLayout(new FlowLayout());

        //this.Layout
        GroupLayout frameLayout = new GroupLayout(this);
        this.setLayout(frameLayout);

        frameLayout.setHorizontalGroup(
                frameLayout.createSequentialGroup()
                .addGroup(frameLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(statsPanel)
                .addComponent(buttonPanel)
                .addComponent(feedbackPanel))
        );
        frameLayout.setVerticalGroup(
                frameLayout.createSequentialGroup()
                .addComponent(statsPanel)
                .addComponent(buttonPanel)
                .addComponent(feedbackPanel)
        );
    }
    */

    public void addBeginButtons()
    {
        buttonPanel.setVisible(false);
        buttonPanel.removeAll();

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

        buttonPanel.setVisible(true);
    }


    public void addSwitchWeaponButtons()//doesn't count as a turn
    {
        buttonPanel.setVisible(false);
        buttonPanel.removeAll();

        for(EquipableItem weapon : combat.player.equipableInventory)
        {
            if(weapon instanceof Weapon)
            {
                JButton button = new JButton(weapon.getName());
                buttonPanel.add(button);
                button.setVisible(true);
                weaponButtons.put(button,(Weapon) weapon);
                button.addActionListener(this);
            }
        }
        buttonPanel.setVisible(true);
        /**
         *
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

    private void setFeedbackText(String feedback)
    {
        feedbackField.setVisible(false);
        feedbackField.setText(feedback);
        feedbackField.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == attackButton)
        {
            combat.takeTurn(combat.player.currentAttack); //the player decides to attack
            setFeedbackText(combat.player.name + " attacked " + combat.opponent.name + "!");
        }
        if (e.getSource() == itemButton)
            addItemButtons();//Allows character to choose which item to use

        if (useableItemButtons.containsKey(e.getSource()))
        {
            UseableItem item = useableItemButtons.get(e.getSource());
            combat.takeTurn(item.combatUse);//uses the item the player chooses
            setFeedbackText(combat.player.name + " used " + item.getName());
            addBeginButtons();
        }

        if(weaponButtons.containsKey(e.getSource()))
        {
            Weapon weapon = weaponButtons.get(e.getSource());
            combat.player.setEquippedWeapon(weapon);
            setFeedbackText(combat.player.name + " equipped " + weapon.getName());
            addBeginButtons();
        }

        if (e.getSource() == switchWeaponButton)
            addSwitchWeaponButtons();
        if (e.getSource() == runButton)
        {
            setFeedbackText(combat.playerRunsAway());
            dispose();
        }

    }
}
