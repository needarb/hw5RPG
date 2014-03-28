package edu.macalester.comp124.hw5;

import javax.swing.*;

/**
 * Created by needa_000 on 3/27/14.
 */
public class BackStoryGUI extends JFrame
{
    private JTextArea textArea;
    public BackStoryGUI()
    {
        setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
        textArea = new JTextArea();


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
