/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.controller.state;

import java.util.Scanner;

import com.intellectual_systems.command.LoadGameDataCommand;
import com.intellectual_systems.controller.GameEngine;
import com.intellectual_systems.controller.GameState;



/**
 *
 * @author Jonathan
 */
public class LoadState implements GameState {
    private static final Scanner scanner = new Scanner(System.in);
    private GameEngine gameEngine;

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void renderCurrentState() {
        System.out.println("Rendering Load State");

        try {
            System.out.println("Welcome to the Intellectual Systems Game!");
            System.out.println("Please select the data format to load the game:");
            System.out.println("1. JSON");
            System.out.println("2. XML");
            System.out.println("3. CSV");
            System.out.print("Enter your choice (1-3): ");
            String choice = scanner.next();
            scanner.nextLine();

            System.out.print("Enter the file path to load the game data: ");
            String filePath = scanner.nextLine();

            System.out.println("Loading game data from " + filePath + " in format " + choice);
            LoadGameDataCommand loadCommand = new LoadGameDataCommand(gameEngine, filePath, choice);
            loadCommand.execute();

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            gameEngine.renderCurrentState();
        }

    }

    @Override
    public void renderNextState() {
        gameEngine.setState(new StartState(gameEngine));
        gameEngine.renderCurrentState();
    }
}