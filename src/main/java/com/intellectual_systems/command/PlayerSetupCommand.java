/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.command;

import java.util.List;

import com.intellectual_systems.controller.GameEngine;
import com.intellectual_systems.model.Player;
import com.intellectual_systems.model.Turn;

/**
 *
 * @author Jonathan
 */
public class PlayerSetupCommand implements Command {
    private final GameEngine gameEngine;
    private final List<Player> players;

    public PlayerSetupCommand(GameEngine gameEngine, List<Player> players) {
        this.gameEngine = gameEngine;
        this.players = players;
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    @Override
    public void execute() {
        if (players == null || players.isEmpty()) {
            System.out.println("No players to set up.");
            gameEngine.renderCurrentState();
            return;
        }
        Turn SelectPlayerTurn = new Turn(null);
        SelectPlayerTurn.setCurrentAnswer(String.valueOf(players.size()));
        gameEngine.addPlayerGameEvent("Select Player Count", SelectPlayerTurn);
        gameEngine.setPlayers(this.players);
        gameEngine.initializeTurnManager();
        for (Player player : players) {
            Turn EnterPlayerNameTurn = new Turn(player);
            EnterPlayerNameTurn.setCurrentAnswer(player.getUsername());
            gameEngine.addPlayerGameEvent("Enter Player Name", EnterPlayerNameTurn);
        }
        
        gameEngine.renderNextState();
    }
}
