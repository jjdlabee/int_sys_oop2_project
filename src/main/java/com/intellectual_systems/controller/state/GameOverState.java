/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.controller.state;

import java.util.Scanner;

import com.intellectual_systems.command.GameOverCommand;
import com.intellectual_systems.controller.GameEngine;
import com.intellectual_systems.controller.GameState;

/**
 *
 * @author Jonathan
 */
public class GameOverState implements GameState {
    private final Scanner scanner = new Scanner(System.in);
    private final GameEngine gameEngine;

    public GameOverState(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void renderCurrentState() {
        System.out.println("\nGame Over!");
        
        System.out.println("\nRestart the game? Y/N");
        String choice = scanner.next();
        scanner.nextLine();
        if (choice.equalsIgnoreCase("Y")) {
            gameEngine.updateGameId();
            renderNextState();
            return;
        } else if (!choice.equalsIgnoreCase("N")) {
            System.out.println("Invalid Input, Please try again...");
            renderCurrentState();
        }

        System.out.println("\nPrinting game log to CSV...");
        
        GameOverCommand gameOverCommand = new GameOverCommand(gameEngine);
        gameOverCommand.execute();

        System.out.println("\nThank you for playing!");
        
    }

    @Override
    public void renderNextState() {
        gameEngine.setState(new LoadState(gameEngine));
        gameEngine.renderCurrentState();
    }
}
