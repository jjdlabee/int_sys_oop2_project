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
public class SelectQuestionCommand implements Command {
    private final GameEngine gameEngine;
    private final String categoryName;
    private final int questionIndex;

    public SelectQuestionCommand(GameEngine gameEngine, String categoryName, int questionIndex) {
        this.gameEngine = gameEngine;
        this.categoryName = categoryName;
        this.questionIndex = questionIndex;
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    @Override
    public void execute() {
        String question= gameEngine.getCategoryByName(categoryName).getQuestions().get(questionIndex).getQuestionText();
        int value = gameEngine.getCategoryByName(categoryName).getQuestions().get(questionIndex).getValue();
        gameEngine.getTurnManager().getCurrentTurn().setCurrentQuestion(question);
        gameEngine.getTurnManager().getCurrentTurn().setCurrentQuestionValue(value);
        gameEngine.renderNextState();
    }
}
