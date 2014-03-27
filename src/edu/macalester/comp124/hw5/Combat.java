package edu.macalester.comp124.hw5;

/**
 * Created by Benjamin Mathers on 3/26/14.
 */
public class Combat
{

    Enemy opponent;
    Player player;
    CombatGUI gui;

    public Combat(Player player, Enemy opponent)
    {
        this.opponent = opponent;
        this.player = player;
        gui = new CombatGUI(this);
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

    public String playerWinsBattle()
    {
        //give a message about winning and stat change
        player.attack += opponent.playerAttackBoost;
        player.defense += opponent.playerDefenseBoost;
        //gui.dispose();
        return (player.name + " defeated " + opponent.name + "!");
    }

    public String playerLosesBattle()
    {
        return "You died. Game Over";
        //gui.dispose();
    }

    public String playerRunsAway()
    {
        return player.name + " ran away.";
    }
}
