/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.controller.state;

import java.util.Scanner;

import com.intellectual_systems.command.SelectQuestionCommand;
import com.intellectual_systems.controller.GameEngine;
import com.intellectual_systems.controller.GameState;

/**
 *
 * @author Jonathan
 */
public class QuestionSelectState implements GameState {
    private static final Scanner scanner = new Scanner(System.in);
    private final GameEngine gameEngine;

    public QuestionSelectState(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void renderCurrentState() {
        String categoryName = gameEngine.getTurnManager().getCurrentTurn().getCurrentCategory();
        System.out.println(gameEngine.getGameBoard().renderCategory(categoryName));
        System.out.println("Select a value from the category: " + categoryName);
       try {
            int i;
            for(i = 0; i < gameEngine.getCategoryByName(categoryName).getQuestions().size(); i++){
                System.out.println((i + 1) + ". " + gameEngine.getCategoryByName(categoryName).getQuestions().get(i).getValue());
            }
            System.out.print("\nEnter your choice (1-" + i + "): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            if (!Character.isDigit(Integer.toString(choice).charAt(0)) || (choice < 1 || choice > i)) {
                throw new IllegalArgumentException("Invalid choice. Please select a valid question number.");
            }
            System.out.println("Question " + choice + " has been selected.");
            SelectQuestionCommand selectQuestionCommand = new SelectQuestionCommand(gameEngine, categoryName, choice - 1);
            selectQuestionCommand.execute();
        } catch(IllegalArgumentException e) {
            System.out.println("An error occurred: " + e.getMessage());
            gameEngine.renderCurrentState();
        }
    }

    @Override
    public void renderNextState() {
        gameEngine.setState(new AnswerState(gameEngine));
        gameEngine.renderCurrentState();
    }
} 