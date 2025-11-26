/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.controller.state;

import java.util.Scanner;

import com.intellectual_systems.command.AnswerQuestionCommand;
import com.intellectual_systems.controller.GameEngine;
import com.intellectual_systems.controller.GameState;

/**
 *
 * @author Jonathan
 */
public class AnswerState implements GameState {
    private static final Scanner scanner = new java.util.Scanner(System.in);
    private final GameEngine gameEngine;

    public AnswerState(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void renderCurrentState() {
        String categoryName = gameEngine.getTurnManager().getCurrentTurn().getCurrentCategory();
        int value = gameEngine.getTurnManager().getCurrentTurn().getCurrentQuestionValue();
        System.out.println(gameEngine.getGameBoard().getCell(categoryName, value));
        System.out.println("Rendering the answer state...");
        
       try {
            int i;
            for(i = 0; i < gameEngine.getCategoryByName(categoryName).getQuestionByCategoryAndValue(categoryName, value).getChoices().size(); i++){
                System.out.println((i + 1) + ". " + gameEngine.getCategoryByName(categoryName).getQuestionByCategoryAndValue(categoryName, value).getChoices().get(i));
            }
            System.out.print("Enter your choice (1-" + i + "): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.println("Answer " + choice + " has been selected.");
            AnswerQuestionCommand answerQuestionCommand = new AnswerQuestionCommand(gameEngine, categoryName, value, choice - 1);
            answerQuestionCommand.execute();
        } catch(Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            gameEngine.renderCurrentState();
        }
    }

    @Override
    public void renderNextState() {
        gameEngine.setState(new AnswerState(gameEngine));
    }
}
