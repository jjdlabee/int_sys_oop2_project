/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.model;
import java.util.ArrayList;

/**
 *
 * @author Jonathan
 */
public class Question {
    private final String questionText;
    private final String answer;
    private final String category;
    private final ArrayList<String> choices = new ArrayList<>();
    private final int value;

    public Question(String questionText, ArrayList<String> choices, String answer, String category, int value) {
        this.questionText = questionText;
        this.choices.addAll(choices);
        this.answer = answer;
        this.category = category;
        this.value = value;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getAnswer() {
        return answer;
    }

    public String getCategory() {
        return category;
    }

    public int getValue() {
        return value;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

}
