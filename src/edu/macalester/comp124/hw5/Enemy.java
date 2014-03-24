package edu.macalester.comp124.hw5;
import java.util.*;
/**
 * Created by Benjamin Mathers on 3/24/14.
 */
public abstract class Enemy extends Agent {

    ArrayList<Action> combatActions;

    public Enemy()
    {
        super("enemy");
    }
}
