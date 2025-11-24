/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.parser;
import com.intellectual_systems.model.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.Reader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Jonathan
 */
public class CsvParser implements GameDataParser {

    @Override
    public void parse(String filePath) {
        // Implementation for parsing CSV files
        ArrayList<Category> categories = new ArrayList<>();
        ArrayList<Question> questions = new ArrayList<>();
 
        System.out.println("Parsing CSV file: " + filePath);
        // Add actual parsing logic here

         try {
            Reader in = new FileReader(filePath);
            Iterable<CSVRecord> records = CSVFormat.EXCEL.builder().setHeader().setSkipHeaderRecord(true).get().parse(in);
            for (CSVRecord record : records) {
                ArrayList< String> choices = new ArrayList<>();
                for (char i = 'A'; i < 'E'; i++) {
                    choices.add(record.get("Option"+String.valueOf(i)));
                }

                
                Question question = new Question(
                    record.get("Question"), // questionText
                    choices, // choices
                    record.get("CorrectAnswer"), // answer
                    record.get("Category"), // category
                    Integer.parseInt(record.get("Value"))
                );
                
                if(categories.stream().noneMatch(c -> c.getName().equals(record.get("Category")))) {
                    categories.add(new Category(record.get("Category")));
                }

                questions.add(question);
            }

            for (Category category : categories) {
                for (Question question : questions) {
                    if (question.getCategory().equals(category.getName())) {
                        category.addQuestion(question);
                    }
                }
            }
        } catch (IOException e ) {
        }

        for (Category category : categories) {
            System.out.println(category);
        }
    }

}
