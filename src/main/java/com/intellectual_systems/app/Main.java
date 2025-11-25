/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package com.intellectual_systems.app;
import com.intellectual_systems.controller.*;
import com.intellectual_systems.controller.state.*;
import com.intellectual_systems.parser.*;
import java.util.Scanner;

/**
 *
 * @author Jonathan
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            System.out.println("Welcome to the Intellectual Systems Game!");
            System.out.println("Please select the data format to load the game:");
            System.out.println("1. JSON");
            System.out.println("2. XML");
            System.out.println("3. CSV");
            System.out.print("Enter your choice (1-3): ");
            String choice = scanner.next();
            scanner.nextLine();

            String filePath;
            GameDataParser parser;

            switch (choice) {
                case "1" -> {
                    filePath = "C:/Users/Jonathan/Documents/University of the West Indies/Year 3/Semester 1/Comp 3607 OOP2/Project/sample_game_JSON.json";
                    parser = new JsonParser();
                }
                case "2" ->{
                    filePath = "C:/Users/Jonathan/Documents/University of the West Indies/Year 3/Semester 1/Comp 3607 OOP2/Project/sample_game_XML.xml";
                    parser = new XmlParser();
                }
                case "3" -> {    
                    filePath = "C:/Users/Jonathan/Documents/University of the West Indies/Year 3/Semester 1/Comp 3607 OOP2/Project/sample_game_CSV.csv";
                    parser = new CsvParser();
                } 
                default -> {
                    System.out.println("Invalid choice. Exiting.");
                    return;
                }
            }

            if (parser != null)
                parser.parse(filePath);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());

        }

        GameState initialState = new StartState();

        GameEngine gameEngine = new GameEngine(initialState);
        ((StartState) initialState).setGameEngine(gameEngine);
        gameEngine.render();
    }
}
