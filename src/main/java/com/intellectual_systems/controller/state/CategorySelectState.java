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
public class CategorySelectState implements GameState {
    private static final Scanner scanner = new Scanner(System.in);
    private final GameEngine gameEngine;

    public CategorySelectState(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void renderCurrentState() {
        System.out.println("Select a category for the game.");
       try {
            // for(int i = 0; i < gameEngine.getCategories().size(); i++){
            //     System.out.println((i + 1) + ". " + )
            // }
            // System.out.println("1. JSON");
            // System.out.println("2. XML");
            // System.out.println("3. CSV");
            // System.out.print("Enter your choice (1-3): ");
            // String choice = scanner.next();
            // scanner.nextLine();
            // // Store selected category as needed
            // System.out.println("Category " + category + " has been selected.");
            // // After selection, transition to the next state
            // // gameEngine.setState(new NextState(gameEngine));
        } catch(Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            gameEngine.renderCurrentState();
        }
    }

    @Override
    public void renderNextState() {
        // Transition to the next state after category selection
        // gameEngine.setState(new NextState(gameEngine));
    }

}