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
        System.out.println(gameEngine.getGameBoard().renderQuestionValue(categoryName, value));
        System.out.println("Choose your answer for the question valued at " + value + " in category " + categoryName + ": \n");
        
       try {
            char i;
            for(i = 'A'; i < 'A' + gameEngine.getCategoryByName(categoryName).getQuestionByCategoryAndValue(categoryName, value).getChoices().size(); i++){
                System.out.println(i + ". " + gameEngine.getCategoryByName(categoryName).getQuestionByCategoryAndValue(categoryName, value).getChoices().get(i - 'A'));
            }

            System.out.print("\nEnter your choice (A-" + (char)(i - 1) + "): ");
            char choice = scanner.next().charAt(0);
            scanner.nextLine(); 

            System.out.println("Answer " + choice + " has been selected.");

            AnswerQuestionCommand answerQuestionCommand = new AnswerQuestionCommand(gameEngine, categoryName, value, choice);
            answerQuestionCommand.execute();
        } catch(Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            gameEngine.renderCurrentState();
        }
    }

    @Override
    public void renderNextState() {
        gameEngine.setState(new EndTurnState(gameEngine));
        gameEngine.renderCurrentState();
    }
}
