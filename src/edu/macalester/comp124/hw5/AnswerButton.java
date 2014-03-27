package edu.macalester.comp124.hw5;

import javax.swing.*;

/**
 * Created by needa_000 on 3/26/14.
 */
public class AnswerButton extends JButton
{
    private int id;
    public AnswerButton(String text, int id)
    {
        super(text);
        this.id = id;
    }

    public int getID()
    {
        return id;
    }
}
