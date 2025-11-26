/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.controller;
import java.util.List;

import com.intellectual_systems.model.Category;
import com.intellectual_systems.model.GameBoard;
import com.intellectual_systems.model.Player;


/**
 *
 * @author Jonathan
 */
public class GameEngine {
    GameState state;
    private List<Player> players;
    private List<Category> categories;
    private GameBoard gameBoard;
    private TurnManager turnManager;

    public GameEngine(GameState startState) {
        this.state = startState;
    }

    //Accessor methods
    public List<Player> getPlayers() {
        return this.players;
    }
    public GameBoard getGameBoard(){
        return this.gameBoard;
    }
    public List<Category> getCategories() {
        return this.categories;
    }
    public TurnManager getTurnManager(){
        return this.turnManager;
    }
    public Category getCategoryByName(String name) {
        for (Category category : categories) {
            if (category.getName().equals(name)) {
                return category;
            }
        }
        return null; // or throw an exception if preferred
    }
    public int getTotalTurns() {
        return categories.size() * categories.get(0).getQuestions().size(); 
    }

    //Mutator methods
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    public void setGameBoard(GameBoard gameBoard){
        this.gameBoard = gameBoard;
    }
    public void setState(GameState state) {
        this.state = state;
    }

    //Game state methods
    public void renderCurrentState() {
        state.renderCurrentState();
    }
    public void renderNextState() {
        state.renderNextState();
    }

    public void initializeTurnManager(){
        this.turnManager = new TurnManager(players);
    }

}
