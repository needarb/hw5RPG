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

    boolean movingForward;

    public Enemy(String type, int speed, int health, int playerAttackBoost, int playerDefenseBoost)
    {
        super(type, speed, health);
        movingForward = true;
        this.playerAttackBoost = playerAttackBoost;
        this.playerDefenseBoost = playerDefenseBoost;
        combatActions = new ArrayList<>();

    }

    public void setPath(int[] pointA, int[] pointB)//sets the path of the Enemy
    {
        pathPointA = pointA;
        pathPointB = pointB;
    }

    public void switchMovingForward()
    {
        movingForward = !movingForward;
    }

    public int[] moveEnemy()
    {
        if(pathPointA[0] == pathPointB[0] && pathPointA[1] == pathPointB[1])//enemy doesn't move at all
            return new int[]{this.x,this.y};

        //switches the direction the enemy moves in if the enemy is at that location
        int[] temp = {this.x, this.y};
        if(temp == pathPointA || temp == pathPointB)
            switchMovingForward();

        int tempX = this.x;
        int tempY = this.y;

        int[] newLoc;
        //move vertically
        if (pathPointA[0] == pathPointB[0])
        {
            if(movingForward)
                tempY++;
            else
                tempY--;
        }
        //moving horizontally
        else
        {
            if(movingForward)
                tempX++;
            else
                tempX--;
        }

        newLoc = new int[]{tempX, tempY};
        return newLoc;

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

            if(combatActions.size() == 0) //if enemy is out of moves
            {
                combatActions.add(new AttackAction(5,100,100,"Struggle"));
            }

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

        return "uh-oh. There's an error in our code";
    }

}
