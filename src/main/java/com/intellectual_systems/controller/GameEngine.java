/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.controller;
import java.util.List;

import com.intellectual_systems.logging.GameEvent;
import com.intellectual_systems.model.Category;
import com.intellectual_systems.model.GameBoard;
import com.intellectual_systems.model.Player;
import com.intellectual_systems.model.Turn;


/**
 *
 * @author Jonathan
 */
public class GameEngine {
    GameState state;
    private String gameId;
    private static int gamesPlayed = 0;

    private List<Player> players;
    private List<Category> categories;

    private GameBoard gameBoard;
    private TurnManager turnManager;
    private final GameEvent gameEvent;

    public GameEngine(GameState startState) {
        this.state = startState;
        gamesPlayed++;
        this.gameId = String.format("GAME%03d", gamesPlayed);
        this.gameEvent = new GameEvent();
    }

    //Accessor methods
    public String getGameId() {
        return this.gameId;
    }
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
        return null; 
    }
    public int getTotalTurns() {
        return categories.size() * categories.get(0).getQuestions().size(); 
    }
    public GameEvent getGameEvent() {
        return this.gameEvent;
    }

    //Mutator methods
    public void updateGameId() {
        gamesPlayed++;
        this.gameId = String.format("GAME%03d", gamesPlayed);
    }
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

    //Logging methods
    public void addPlayerGameEvent(String activity, Turn turn) {
        this.gameEvent.newGameEvent(this.gameId, activity, turn);
    }
    public void addSystemGameEvent(String activity) {
        this.gameEvent.newGameEvent(this.gameId, activity, null);
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
