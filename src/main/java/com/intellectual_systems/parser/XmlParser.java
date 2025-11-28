/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.intellectual_systems.model.Category;
import com.intellectual_systems.model.Question;


/**
 *
 * @author Jonathan
 */
public class XmlParser implements GameDataParser {

    @Override
    public ArrayList<Category> parse(String filePath) {
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
            NodeList optionsList = doc.getElementsByTagName("Options");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                Node optionNode = optionsList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    ArrayList<String> choices = new ArrayList<>();

                    if (optionNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element optionElement = (Element) optionNode;

                        for (int i = 0; i < optionNode.getChildNodes().getLength(); i++) {
                            char optionChar = (char)('A' + i);
                            choices.add(optionElement.getElementsByTagName("Option" + optionChar).item(0).getTextContent());
                        }
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
        return categories;
    }

}
