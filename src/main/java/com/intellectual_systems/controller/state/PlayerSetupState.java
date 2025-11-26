/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.controller.state;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.intellectual_systems.command.PlayerSetupCommand;
import com.intellectual_systems.controller.GameEngine;
import com.intellectual_systems.controller.GameState;
import com.intellectual_systems.model.Player;

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
        System.out.println("\nSetting up players. Please enter player details.");

        System.out.print("Enter number of players:");
        int numPlayers = Integer.parseInt(scanner.nextLine());

        if (numPlayers <= 0 || numPlayers > 4) {
            System.out.println("Invalid number of players. Please try again.");
            gameEngine.renderCurrentState();
        } else {
            for (int i = 1; i <= numPlayers; i++) {
                System.out.print("Enter name for Player " + i + ": ");
                String playerName = scanner.nextLine();

                for(Player p : players) {
                    if(p.getUsername().equalsIgnoreCase(playerName)) {
                        System.out.println("Player name already taken. Please enter a different name.");
                        i--;
                        break;
                    }
                }
                if(players.stream().anyMatch(p -> p.getUsername().equalsIgnoreCase(playerName))) {
                    continue;
                }
                Player player = new Player(playerName);
                players.add(player);
                System.out.println("Player " + i + " named " + playerName + " has been set up.");
            }

            PlayerSetupCommand playerSetupCommand = new PlayerSetupCommand(this.gameEngine, players);
            playerSetupCommand.execute();
        }
    }

    @Override
    public void renderNextState() {
        gameEngine.setState(new CategorySelectState(gameEngine));
        gameEngine.renderCurrentState();
    }
}