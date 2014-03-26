package edu.macalester.comp124.hw5;

/**
 * Created by Benjamin Mathers on 3/26/14.
 */
public class Combat
{

    Enemy opponent;
    Player player;

    public Combat(Player player, Enemy opponent)
    {
        this.opponent = opponent;
        this.player = player;
    }

    public void takeTurn(Action playerAction)
    {

        if(opponent.combatSpeed > player.combatSpeed)
        {
            performOppAction();
            performPlayAction(playerAction);
        }

        else
        {
            performPlayAction(playerAction);
            performOppAction();
        }

    }

    public void performOppAction()
    {
        opponent.performCombatAction(player);
    }

    public void performPlayAction(Action action)
    {
        player.performCombatAction(action, opponent);
    }

}
