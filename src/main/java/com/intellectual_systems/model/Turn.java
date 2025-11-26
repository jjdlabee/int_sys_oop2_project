/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.model;

/**
 *
 * @author Jonathan
 */
public class Turn {
    private final Player player;
    private int turnNumber = 0;
    private String currentCategory;
    private String currentQuestion;
    private String currentAnswer;
    private boolean isCorrect;
    private int currentQuestionValue;

    public Turn(Player player) {
        this.player = player;
    }

    public void setCurrentCategory(String category) {
        this.currentCategory = category;
    }
    public void setCurrentQuestion(String question) {
        this.currentQuestion = question;
    }
    public void setCurrentAnswer(String answer){
        this.currentAnswer = answer;
    }
    public void setCurrentQuestionValue(int value){
        this.currentQuestionValue = value;
    }
    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
        turnNumber++;
    }

    public int getTurnNumber() {
        return this.turnNumber;
    }
    public boolean getIsCorrect() {
        return isCorrect;
    }
    public Player getPlayer() {
        return player;
    }
    public String getCurrentCategory() {
        return currentCategory;
    }
    public String getCurrentQuestion() {
        return currentQuestion;
    }
    public String getCurrentAnswer() {
        return currentAnswer;
    }
    public int getCurrentQuestionValue() {
        return currentQuestionValue;
    }
}
