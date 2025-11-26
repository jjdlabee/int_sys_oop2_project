/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.command;

import java.util.List;

import com.intellectual_systems.controller.GameEngine;
import com.intellectual_systems.model.Player;

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
        gameEngine.setPlayers(this.players);
        gameEngine.initializeTurnManager();
        gameEngine.renderNextState();
    }
}
