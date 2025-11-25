/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.controller.state;

import java.util.Scanner;

import com.intellectual_systems.command.StartCommand;
import com.intellectual_systems.controller.GameEngine;
import com.intellectual_systems.controller.GameState;

/**
 *
 * @author Jonathan
 */
public class StartState implements GameState {
    private final GameEngine gameEngine;
    private static final Scanner scanner = new Scanner(System.in);

    public StartState(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void renderCurrentState() {
        // Implementation for rendering the start state
        System.out.println("Welcome to the Game! Press Start to begin.");

        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            StartCommand  startCommand = new StartCommand(gameEngine, input);
            startCommand.execute();
        }
    }

    @Override
    public void renderNextState() {
        gameEngine.setState(new PlayerSetupState(gameEngine));
        gameEngine.renderCurrentState();
    }

}