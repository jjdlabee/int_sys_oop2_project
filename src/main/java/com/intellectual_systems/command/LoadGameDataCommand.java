/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.command;

import com.intellectual_systems.controller.GameEngine;
import com.intellectual_systems.model.GameBoard;
import com.intellectual_systems.parser.CsvParser;
import com.intellectual_systems.parser.GameDataParser;
import com.intellectual_systems.parser.JsonParser;
import com.intellectual_systems.parser.XmlParser;

/**
 *
 * @author Jonathan
 */
public class LoadGameDataCommand implements Command {
    private final GameEngine gameEngine; 
    private final String filePath;
    private final String format;

    public LoadGameDataCommand(GameEngine gameEngine, String filePath, String format) {
        this.gameEngine = gameEngine;
        this.filePath = filePath;
        this.format = format;
    }

    public void setGameBoard(GameBoard gameBoard) {
        gameEngine.setGameBoard(gameBoard);
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public void parse(){
        GameDataParser parser;
        if (format.equalsIgnoreCase("1")) {
            // Load JSON data
            parser = new JsonParser();
        } else if (format.equalsIgnoreCase("2")) {
            // Load XML data
            parser = new XmlParser();
        } else if (format.equalsIgnoreCase("3")) {
            // Load CSV data
            parser = new CsvParser();
        } else {
            System.out.println("Unsupported format: " + format);
            gameEngine.renderCurrentState();
            return;
        }

        gameEngine.setCategories(parser.parse(filePath));
    }

    public void loadGameBoard(){
        GameBoard gameBoard = new GameBoard(gameEngine.getCategories());
        gameBoard.initializeBoard(gameEngine.getCategories());
        gameBoard.loadQuestions(gameEngine.getCategories());
        gameEngine.setGameBoard(gameBoard);
    }

    @Override
    public void execute() {
        parse();
        loadGameBoard();
        gameEngine.addSystemGameEvent("Load File");
        gameEngine.renderNextState();
    }

}
