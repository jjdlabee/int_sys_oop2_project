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
    private final char choiceIndex;

    public AnswerQuestionCommand(com.intellectual_systems.controller.GameEngine gameEngine, String categoryName, int questionValue, char choiceIndex) {
        this.gameEngine = gameEngine;
        this.categoryName = categoryName;
        this.questionValue = questionValue;
        this.choiceIndex = choiceIndex;
    }

    @Override
    public void execute() {
        String correctAnswer = gameEngine.getCategoryByName(this.categoryName).getQuestionByCategoryAndValue(this.categoryName, this.questionValue).getAnswer();
        String selectedAnswer = Character.toString(choiceIndex);
        gameEngine.getTurnManager().getCurrentTurn().setCurrentAnswer(selectedAnswer);

        if (selectedAnswer.equals(correctAnswer)) {
            int currentScore = gameEngine.getTurnManager().getCurrentTurn().getPlayer().getScore();
            gameEngine.getTurnManager().getCurrentTurn().getPlayer().setScore(currentScore + this.questionValue);
            gameEngine.getTurnManager().getCurrentTurn().setIsCorrect(true);
        } else {
            System.out.println("Incorrect answer. The correct answer was: " + correctAnswer);
            gameEngine.getTurnManager().getCurrentTurn().setIsCorrect(false);
        }
        
        gameEngine.getGameBoard().clearCell(this.categoryName, this.questionValue);
        gameEngine.renderNextState();
    }

}
