/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.command;

import com.intellectual_systems.controller.GameEngine;

/**
 *
 * @author Jonathan
 */
public class StartCommand implements Command {
    private final GameEngine gameEngine;
    private final String input;

    public StartCommand(GameEngine gameEngine, String input) {
        this.gameEngine = gameEngine;
        this.input = input;
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    @Override
    public void execute() {
        if (!input.equalsIgnoreCase("start")) {
            System.out.println("Invalid input. Please type 'Start' to begin.");
            gameEngine.renderCurrentState();
            return;
        }
        gameEngine.addSystemGameEvent("Start Game");
        gameEngine.renderNextState();
    }
}
