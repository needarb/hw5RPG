package edu.macalester.comp124.hw5;

import javax.swing.*;
import java.awt.*;

/**
 * Created by needa_000 on 3/31/2014.
 */
public class StatsGUI extends JFrame
{
    private JLabel health;
    private JLabel attack;
    private JLabel defense;
    private JLabel speed;
    private Player p;
    public StatsGUI(Player p)
    {
        this.p = p;
        p.addStatsGUI(this);
        setSize(300,100);
       setLayout(new FlowLayout());
        health = new JLabel("Health: " + p.healthPoints);
        attack = new JLabel("Attack: " + p.attack);
        defense = new JLabel("Defense " + p.defense);
        speed = new JLabel("Speed " + p.combatSpeed);
       add(health);
       add(attack);
       add(defense);
       add(speed);
       setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       setVisible(true);
    }

    public void updateStats()
    {
        health.setText("Health: " + p.healthPoints);
        attack.setText("Attack: " + p.attack);
        defense.setText("Defense " + p.defense);
        speed.setText("Speed " + p.combatSpeed);
    }
}
