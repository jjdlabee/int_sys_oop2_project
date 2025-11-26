/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.controller.state;

import java.util.Scanner;

import com.intellectual_systems.command.SelectCategoryCommand;
import com.intellectual_systems.controller.GameEngine;
import com.intellectual_systems.controller.GameState;

/**
 *
 * @author Jonathan
 */
public class CategorySelectState implements GameState {
    private static final Scanner scanner = new Scanner(System.in);
    private final GameEngine gameEngine;

    public CategorySelectState(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void renderCurrentState() {
        gameEngine.getGameBoard().renderEntireGameBoard();
        System.out.println("Select a category for the game.");
       try {
            int i;
            for(i = 0; i < gameEngine.getCategories().size(); i++){
                System.out.println((i + 1) + ". " + gameEngine.getCategories().get(i).getName());
            }
            System.out.print("Enter your choice (1-" + i + "): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.println("Category " + choice + " has been selected.");
            SelectCategoryCommand selectCategoryCommand = new SelectCategoryCommand(gameEngine, choice - 1);
            selectCategoryCommand.execute();
        } catch(Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            gameEngine.renderCurrentState();
        }
    }

    @Override
    public void renderNextState() {
        gameEngine.setState(new QuestionSelectState(gameEngine));
        gameEngine.renderCurrentState();
    }

}