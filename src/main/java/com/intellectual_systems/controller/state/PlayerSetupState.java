/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.controller.state;

import com.intellectual_systems.controller.GameEngine;
import com.intellectual_systems.controller.GameState;
import com.intellectual_systems.model.Player;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public class PlayerSetupState implements GameState {
    private final GameEngine gameEngine;
    private static final Scanner scanner = new Scanner(System.in);

    public PlayerSetupState(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void renderCurrentState() {
        List<Player> players = new ArrayList<>();
        System.out.println("Setting up players. Please enter player details.");

        System.out.println("Enter number of players:");
        int numPlayers = Integer.parseInt(scanner.nextLine());

        if (numPlayers <= 0 || numPlayers > 4) {
            System.out.println("Invalid number of players. Please try again.");
            gameEngine.renderCurrentState();
        } else {
            for (int i = 1; i <= numPlayers; i++) {
                System.out.println("Enter name for Player " + i + ":");
                String playerName = scanner.nextLine();
                Player player = new Player(playerName);
                players.add(player);
                System.out.println("Player " + i + " named " + playerName + " has been set up.");
            }

            gameEngine.setPlayers(players);
            // After setup, transition to the next state, e.g., CategorySelectState
            //gameEngine.setState(new CategorySelectState(gameEngine));
        }
    }
}