package edu.macalester.comp124.hw5;

import javax.swing.*;
import javax.swing.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Benjamin Mathers on 3/31/14.
 */
public class BeginScreen extends JFrame implements ActionListener
{
    private JPanel textPanel;
    private JTextArea textArea;
    private JButton done;

    public BeginScreen()
    {
        textPanel = new JPanel();
        textArea = new JTextArea();
        textArea.setText(getBeginText());

        this.setVisible(true);
        this.setSize(500,400);
        this.setResizable(true);

        textPanel.setVisible(true);
        textArea.setVisible(true);

        textArea.setSize(400,350);

        textPanel.setSize(400,400);

        textPanel.add(textArea);

        done = new JButton("Done");
        done.addActionListener(this);

        textPanel.add(done);

        this.add(textPanel);
    }

    private String getBeginText()
    {
        String begin = "The Evil Bam spirit has invaded your peaceful kingdom,\n possessing every animal and bending them to its evil will.\n Where the possessed animals can do no harm, the " +
                "\nspirit sends its despicable servants: the Black Knights.\n As its figurehead the Bam Spirit has chosen the most majestic beast in all the land: the Panther,\n" +
                "thus creating the dreaded Bamther. \n You are naught but a peasant,\n but you are the only one still alive who can fight against this monstrosity.\n But you are not" +
                " ready yet. \nYou must find better weapons and hone your fighting skills\n to defeat the Bamther. Your Quest has begun. Good Luck.";

        return begin;
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==done)
            dispose();
    }
}
