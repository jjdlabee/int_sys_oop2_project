/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.controller.state;

import java.util.Scanner;

import com.intellectual_systems.controller.GameEngine;
import com.intellectual_systems.controller.GameState;

/**
 *
 * @author Jonathan
 */
public class GameOverState implements GameState {
    private static final Scanner scanner = new Scanner(System.in);
    private final GameEngine gameEngine;

    public GameOverState(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void renderCurrentState() {
        System.out.println("Game Over!");
        
        System.out.println("Final Scores:");
        gameEngine.getPlayers().forEach(player -> {
            System.out.println(player.getUsername() + ": " + player.getScore() + " points");
        });

        System.out.println("Restart the game? Y/N");
        String choice = scanner.next();
        scanner.nextLine();
        if (choice.equalsIgnoreCase("Y")) {
            renderNextState();
        } else {
            System.out.println("Thank you for playing!");
        }
    }

    @Override
    public void renderNextState() {
        gameEngine.setState(new LoadState(gameEngine));
        gameEngine.renderCurrentState();
    }
}
