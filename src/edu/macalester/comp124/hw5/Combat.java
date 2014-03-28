package edu.macalester.comp124.hw5;

import javax.swing.*;

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
        gui.clearFeedbackText();
        if(opponent.combatSpeed > player.combatSpeed)
        {
            performOppAction();

            if(checkCombatOver())
                return;

            performPlayAction(playerAction);

            if(checkCombatOver())
                return;
        }

        else
        {
            performPlayAction(playerAction);

            if(checkCombatOver())
                return;

            performOppAction();

            if(checkCombatOver())
                return;
        }

    }

    public void performOppAction()
    {
       String feedback = opponent.performCombatAction(player);
       gui.setFeedbackText(feedback);
    }

    public void performPlayAction(Action action)
    {
       String feedback = player.performCombatAction(action, opponent);
       gui.setFeedbackText(feedback);
    }

    public void playerWinsBattle()
    {
        //give a message about winning and stat change
        player.attack += opponent.playerAttackBoost;
        player.defense += opponent.playerDefenseBoost;
        gui.setFeedbackText(player.name + " defeated " + opponent.name + "!");
        gui.setFeedbackText("Attack Boost: " + opponent.playerAttackBoost);
        gui.setFeedbackText("Defense Boost: " + opponent.playerDefenseBoost);
        gui.addEndButton();
    }

    private boolean checkCombatOver()
    {
        if(player.healthPoints <= 0)
        {
            playerLosesBattle();
            return true;
        }

        else if (opponent.healthPoints <= 0)
        {
            playerWinsBattle();
            return true;
        }

        return false;
    }

    public void playerLosesBattle()
    {
        gui.setFeedbackText("You died. Game Over");
        gui.addEndButton();
    }

    public void playerRunsAway()
    {
        gui.setFeedbackText(player.name + " ran away.");
        gui.addEndButton();
    }
}
