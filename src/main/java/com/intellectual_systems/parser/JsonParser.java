/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.parser;
import com.intellectual_systems.model.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *
 * @author Jonathan
 */
public class JsonParser implements GameDataParser {

    @Override
    public void parse(String filePath) {
        // Implementation for parsing JSON files will go here
        ArrayList<Category> categories = new ArrayList<>();
        ArrayList<Question> questions = new ArrayList<>();
 
        System.out.println("Parsing JSON file: " + filePath);
        // Add actual parsing logic here

         try {
            JSONArray data = (JSONArray) new JSONParser().parse(new FileReader(filePath));
            for (Object obj : data) {
                JSONObject record = (JSONObject) obj;

                ArrayList< String> choices = new ArrayList<>();
                JSONObject choicesObject = (JSONObject) record.get("Options");
                for (Object choiceObj : choicesObject.values()) {
                    choices.add((String) choiceObj);
                }

                Question question = new Question(
                    (String) record.get("QuestionText"), // questionText
                    choices, // choices
                    (String) record.get("Answer"), // answer
                    (String) record.get("Category"), // category
                    ((Long) record.get("Value")).intValue() // value
                );

                questions.add(question);
            }
            
            for (Category category : categories) {
                for (Question question : questions) {
                    if (question.getCategory().equals(category.getName())) {
                        category.addQuestion(question);
                    }
                }
            }

        } catch (IOException | ParseException e) {
        }

        for (Category category : categories) {
            System.out.println(category);
        }
    }
       
}
