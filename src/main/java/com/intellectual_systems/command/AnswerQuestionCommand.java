/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.command;

/**
 *
 * @author Jonathan
 */
public class AnswerQuestionCommand implements Command {
    private final com.intellectual_systems.controller.GameEngine gameEngine;
    private final String categoryName;
    private final int questionValue;
    private final int choiceIndex;

    public AnswerQuestionCommand(com.intellectual_systems.controller.GameEngine gameEngine, String categoryName, int questionValue, int choiceIndex) {
        this.gameEngine = gameEngine;
        this.categoryName = categoryName;
        this.questionValue = questionValue;
        this.choiceIndex = choiceIndex;
    }

    @Override
    public void execute() {
        // Logic to process the answer
        System.out.println("Processing answer for category: " + categoryName + ", value: " + questionValue + ", choice index: " + choiceIndex);
        // Here you would typically check if the answer is correct and update the game state accordingly
    }

}
