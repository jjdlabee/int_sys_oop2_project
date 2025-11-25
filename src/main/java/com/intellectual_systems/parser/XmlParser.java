/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.parser;

import com.intellectual_systems.model.*;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import java.util.ArrayList;


/**
 *
 * @author Jonathan
 */
public class XmlParser implements GameDataParser {

    @Override
    public void parse(String filePath) {
        // Implementation for parsing XML files
        ArrayList<Category> categories = new ArrayList<>();
        ArrayList<Question> questions = new ArrayList<>();
 
        System.out.println("Parsing XML file: " + filePath);
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("QuestionItem");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    ArrayList<String> choices = new ArrayList<>();
                    NodeList optionsList = eElement.getElementsByTagName("Option");
                    for (int i = 0; i < optionsList.getLength(); i++) {
                        choices.add(optionsList.item(i).getTextContent());
                    }

                    Question question = new Question(
                        eElement.getElementsByTagName("QuestionText").item(0).getTextContent(), // questionText
                        choices, // choices
                        eElement.getElementsByTagName("CorrectAnswer").item(0).getTextContent(), // answer
                        eElement.getElementsByTagName("Category").item(0).getTextContent(), // category
                        Integer.parseInt(eElement.getElementsByTagName("Value").item(0).getTextContent()) // value
                    );

                    if(categories.stream().noneMatch(c -> c.getName().equals(question.getCategory()))) {
                        categories.add(new Category(question.getCategory()));
                    }
                    questions.add(question);
                }
            }
            
            for (Category category : categories) {
                for (Question question : questions) {
                    if (question.getCategory().equals(category.getName())) {
                        category.addQuestion(question);
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            
        }
    }

}
