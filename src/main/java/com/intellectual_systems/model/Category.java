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
public class Category {
    private final String name;
    private final ArrayList<Question> questions = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public Question getQuestionByCategoryAndValue(String categoryName, int value) {
        if (!this.name.equals(categoryName)) {
            return null; // or throw an exception if preferred
        }
        Category category = this;
        if (category != null) {
            for (Question question : category.getQuestions()) {
                if (question.getValue() == value) {
                    return question;
                }
            }
        }
        return null; // or throw an exception if preferred
    }

    @Override
    public String toString() {
        String s = "Category{" + "name=" + name; 
        for(Question q : questions) {
            s += "\n\t" + q.toString();
        }
        s += "\n}";
        return s;
    }
}
