package edu.macalester.comp124.hw5;
import java.util.*;
/**
 * Created by Benjamin Mathers on 3/24/14.
 */
public abstract class Enemy extends Agent {

    ArrayList<Action> combatActions;

    public Enemy(String type, int speed, int health)
    {
        super(type, speed, health);
    }

    public void performCombatAction(Agent opponent)
    {
        Random generator = new Random();
        Action action;

        do
        {
        int length = combatActions.size();
        int index = generator.nextInt(length);
        action = combatActions.get(index);

        if (!action.useableAction())//removes the action if it's not useable
            combatActions.remove(index);

        }while(!action.useableAction());

        if (action instanceof CombatItemAction) //uses a potion or similar item on the enemy itself
            ((CombatItemAction) action).performAction(this);
        if (action instanceof AttackAction) //uses an attack on the Enemy's opponent
            ((AttackAction) action).performAction(opponent);
    }
}
