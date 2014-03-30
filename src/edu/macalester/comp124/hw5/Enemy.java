package edu.macalester.comp124.hw5;
import java.util.*;
/**
 * Created by Benjamin Mathers on 3/24/14.
 */
public abstract class Enemy extends Agent {

    public ArrayList<Action> combatActions;

    int playerAttackBoost; //the attack boost the player gets for beating the Enemy
    int playerDefenseBoost; // the defense boost the player gets for beating the Enemy

    //two sets of coordinates that the enemy walks in between
    int[] pathPointA;
    int[] pathPointB;

    public Enemy(String type, int speed, int health, int playerAttackBoost, int playerDefenseBoost)
    {
        super(type, speed, health);
        this.playerAttackBoost = playerAttackBoost;
        this.playerDefenseBoost = playerDefenseBoost;
        combatActions = new ArrayList<>();
    }

    public void setPath(int[] pointA, int[] pointB)//sets the path of the Enemy
    {
        pathPointA = pointA;
        pathPointB = pointB;
    }

    public String performCombatAction(Agent opponent)
    {
        Random generator = new Random();
        Action action;

        do//makes sure the action is useable
        {
        int length = combatActions.size();
        int index = generator.nextInt(length);
        action = combatActions.get(index);

        if (!action.useableAction())//removes the action if it's not useable
            combatActions.remove(index);

        }while(!action.useableAction());

        if (action instanceof CombatItemAction) //uses a potion or similar item on the enemy itself. Returns feedback
        {
            ((CombatItemAction) action).performAction(this);
            return (this.name + " used " + action.getName());
        }

        if (action instanceof AttackAction)//uses an attack on the Enemy's opponent. returns feedback
        {
            ((AttackAction) action).performAction(opponent);
            return (this.name + " attacked you with " + action.getName() + "!");
        }

        return "uh-oh. There's and error in our code";
    }

}
