package edu.macalester.comp124.hw5;

import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by needa_000 on 3/26/14.
 */
public class CharacterCreationGUI extends JFrame implements ActionListener
{

    public static void main(String[] args)
    {
        CharacterCreationGUI g = new CharacterCreationGUI();
        g.askNextQuestion();
    }

    public static final String[] STANDARD_QUESTIONS = {"gender", "height", "weight"};
    public static final String[] FAVORITE_QUESTIONS = {"food", "boy band", "method of dying", "mathematical symbol", "method of transportation", "type of music"};

    public static final HashMap<String, String[]> STANDARD_ANSWERS = new HashMap<>();
    public static final HashMap<String, String[]> FAVORITE_ANSWERS = new HashMap<>();

    static
    {
        // --- <answer>:<HealthBoost>,<AttackBoost>,<DefenseBoost>,<SpeedBoost> --- //
        STANDARD_ANSWERS.put(STANDARD_QUESTIONS[0], new String[]{"Male:0,0,0,0", "Female:0,0,0,0", "Other:0,0,0,0"});
        STANDARD_ANSWERS.put(STANDARD_QUESTIONS[1], new String[]{"3 Feet:5,2,4,-3", "4 Feet:4,2,2,0", "5 Feet:3,2,2,1", "6 Feet:4,3,0,3"});
        STANDARD_ANSWERS.put(STANDARD_QUESTIONS[2], new String[]{"100 Lbs:0,1,1,6", "150 Lbs:2,2,2,3", "200 Lbs:4,3,3,0", "250 Lbs:6,3,4,-3"});

        FAVORITE_ANSWERS.put(FAVORITE_QUESTIONS[0], new String[]{"Pizza:0,1,0,0", "Salad:2,0,0,0", "Ice Cream:0,0,1,0", "Jalapeño:0,0,0,1"});
        FAVORITE_ANSWERS.put(FAVORITE_QUESTIONS[1], new String[]{"One Direction:0,0,0,1", "Backstreet Boys:1,0,0,0", "NSYNC:0,0,1,0", "Jones Brothers:0,1,0,0"});
        FAVORITE_ANSWERS.put(FAVORITE_QUESTIONS[2], new String[]{"Starvation:2,0,0,0", "Electrocution:0,1,0,0", "Decapitation:0,0,1,0", "Animal Mauling:0,0,0,1"});
        FAVORITE_ANSWERS.put(FAVORITE_QUESTIONS[3], new String[]{"Pi - π:2,0,0,0", "Infinity - ∞:0,1,0,0", "Multiple Integral - ∭:0,0,1,0", "Factorial - !:0,0,0,1"});
        FAVORITE_ANSWERS.put(FAVORITE_QUESTIONS[4], new String[]{"Bulldozer:2,0,0,0", "Train:0,0,1,0", "Submarine:0,1,0,0", "Airplane:0,0,0,1"});
        FAVORITE_ANSWERS.put(FAVORITE_QUESTIONS[5], new String[]{"Club:0,0,0,1", "Classical:2,0,0,0", "Jazz:0,0,1,0", "Epic Soundtracks:0,1,0,0"}); //Ke$ha,

    }

    private JLabel questionLabel;
    private JPanel answerPanel;
    private JPanel questionPanel;

    private int question;
    private int part;

    public CharacterCreationGUI()
    {
        setSize(500, 700);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        questionLabel = new JLabel();
        questionPanel = new JPanel();
        questionPanel.setLayout(new FlowLayout());
        questionPanel.add(questionLabel);
        answerPanel = new JPanel();
        answerPanel.setLayout(new FlowLayout());

        add(questionPanel);
        add(answerPanel);

        question = 0;
        part = 0;
        setVisible(true);
    }

    public void askNextQuestion()
    {
        answerPanel.removeAll();
        String[] questions;
        HashMap<String, String[]> answers;
        String prompt = "What is your character's ";
        if (part == 0)
        {
            questions = STANDARD_QUESTIONS;
            answers = STANDARD_ANSWERS;
        }
        else
        {
            questions = FAVORITE_QUESTIONS;
            answers = FAVORITE_ANSWERS;
            prompt += "favorite ";
        }
        questionLabel.setText(prompt + questions[question] + "?");

        int id = 0;
        for (String answer : answers.get(questions[question]))
        {
            AnswerButton button = new AnswerButton(answer.split(":")[0],id);
            button.addActionListener(this);
            answerPanel.add(button);
            id++;
        }
        repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (!(e.getSource() instanceof AnswerButton))
            return;

        String[] questions;
        HashMap<String, String[]> answers;
        if (part == 0)
        {
            questions = STANDARD_QUESTIONS;
            answers = STANDARD_ANSWERS;
        }
        else
        {
            questions = FAVORITE_QUESTIONS;
            answers = FAVORITE_ANSWERS;
        }


        AnswerButton button = (AnswerButton) e.getSource();
        String text = button.getText();
        for (String s : answers.keySet())
            System.out.println(s);

        String[] values = answers.get(questions[question])[button.getID()].split(":")[1].split(",");
        System.out.println("Health increased by: " + values[0]);
        System.out.println("Attack increased by: " + values[1]);
        System.out.println("Defense increased by: " + values[2]);
        System.out.println("Speed increased by: " + values[3]);

        if (question >= 2 && part == 0)
        {
            part = 1;
            question = 0;
        }
        else if (question >= 5 && part == 1)
        {
            System.out.println("DONE!");
            System.exit(0);
        }

        question++;
        askNextQuestion();
    }
}
