package edu.macalester.comp124.hw5;

import javax.print.DocFlavor;
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by needa_000 on 3/26/14.
 */
public class CharacterCreationGUI extends JFrame implements ActionListener
{

    public static void main(String[] args)
    {
     //   playSound("test.wav");
      // CharacterCreationGUI g = new CharacterCreationGUI();
     //   g.askNextQuestion();
    }

    public static final String[] STAT_NAMES = {"Health","Attack","Defense","Speed"};

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


    private JPanel questionPanel;
    private JPanel statsPanel;
    private JPanel answerPanel;

    private JLabel questionLabel;
    private JLabel[] statsLabels;

    private int question;
    private int part;

    private Player player;

    private int[] stats;

    public CharacterCreationGUI(Game game,Player player)
    {
        this.player = player;

        stats = new int[4];
        for (int i = 0; i < stats.length; i++)
            stats[i] = 0;

        setSize(750, 300);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        questionPanel = new JPanel();
        questionPanel.setLayout(new FlowLayout());
        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Courier New",Font.BOLD,18));
        questionPanel.add(questionLabel);

        statsPanel = new JPanel();
        statsPanel.setLayout(new FlowLayout());
        statsLabels = new JLabel[4];
        for (int i = 0; i < statsLabels.length; i++)
        {
            statsLabels[i] = new JLabel(STAT_NAMES[i] + ": 0 ");
            statsLabels[i].setFont(new Font("Courier New",Font.PLAIN,16));
            statsPanel.add(statsLabels[i]);
        }


        answerPanel = new JPanel();
        answerPanel.setLayout(new FlowLayout());

        add(questionPanel);
        add(statsPanel);
        add(answerPanel);

        question = 0;
        part = 0;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        askNextQuestion();
    }


    /**
     *  Changes the
     */
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
        else if(part == 1)
        {
            questions = FAVORITE_QUESTIONS;
            answers = FAVORITE_ANSWERS;
            prompt += "favorite ";
        }
        else
        {
            questionLabel.setFont(new Font("Courier New",Font.BOLD,18));
            questionLabel.setText("Your character's stats:");
            AnswerButton button =new AnswerButton("Start Game",-1);
            button.addActionListener(this);
            answerPanel.add(button);
            return;
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
        AnswerButton button = (AnswerButton) e.getSource();

        if(button.getID() == -1)
        {
            System.out.println("Starting the game!");
            this.setVisible(false);
            return;
        }
        if(question == FAVORITE_QUESTIONS.length-1)
            startMusic(button.getText());

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

        String[] values = answers.get(questions[question])[button.getID()].split(":")[1].split(","); //Get the stats values via: Get the possible answers array,get the answer associated with the button ID, get the values part of the answer, get the individual values

        //Update stats
        stats[0] += Integer.parseInt(values[0]);
        stats[1] += Integer.parseInt(values[1]);
        stats[2] += Integer.parseInt(values[2]);
        stats[3] += Integer.parseInt(values[3]);
        //Update Labels
        statsLabels[0].setText(STAT_NAMES[0] + ": " + stats[0]);
        statsLabels[1].setText(STAT_NAMES[1] + ": " + stats[1]);
        statsLabels[2].setText(STAT_NAMES[2] + ": " + stats[2]);
        statsLabels[3].setText(STAT_NAMES[3] + ": " + stats[3]);


        if (question >= 2 && part == 0)
        {
            part = 1;
            question = 0;
        }
        else if (question >= 5 && part == 1)
        {
            System.out.println("\nDONE!");
            System.out.println("Final Stats: ");
            System.out.println(STAT_NAMES[0] + ": " + stats[0] + " ");
            System.out.println(STAT_NAMES[1] + ": " + stats[1] + " ");
            System.out.println(STAT_NAMES[2] + ": " + stats[2] + " ");
            System.out.println(STAT_NAMES[3] + ": " + stats[3] + " ");



            player.setHealth(stats[0]);
            player.setAttack(stats[1]);
            player.setDefense(stats[2]);
            player.setSpeed(stats[3]);

            for(JLabel l:statsLabels)
                l.setFont(new Font("Courier New",Font.PLAIN,26));
            part = -1;
            question = -1;
        }
        else
            question++;
        askNextQuestion();
    }

    public static final String MUSIC_LOC = "music/";
    public void startMusic(String type)
    {
        switch(type.toLowerCase())
        {
            case("jazz"):
                playSound(MUSIC_LOC + "jazz1.wav");
                break;
            case("epic soundtracks"):
                playSound(MUSIC_LOC + "epicsoundtracks1.wav");
                break;
            case("classical"):
                playSound(MUSIC_LOC + "classical1.wav");
                break;
            case("club"):
                playSound(MUSIC_LOC + "club1.wav");
                break;


        }
    }

    public  void playSound(String fileName)
    {
       /* System.out.println(fileName);
        URL fileLoc = CharacterCreationGUI.class.getResource(fileName);
        System.out.println(fileLoc);
        System.out.println(fileLoc.getFile());
        AudioClip clip = Applet.newAudioClip(fileLoc);
        clip.play(); */
        SoundPlayer sp = new SoundPlayer(fileName);
        new Thread(sp).start();
    }


    class SoundPlayer implements Runnable
    {
        private String fileName;
        public SoundPlayer(String fileName)
        {
            this.fileName = fileName;
        }
        public void run()
        {
           // System.out.println(fileName);
            URL fileLoc = CharacterCreationGUI.class.getResource(fileName);
          //  System.out.println(fileLoc);
          //  System.out.println(fileLoc.getFile());
            AudioClip clip = Applet.newAudioClip(fileLoc);
            clip.play();
        }
    }
}
