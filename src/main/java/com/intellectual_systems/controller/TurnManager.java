/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.controller;

import java.util.ArrayList;
import java.util.List;

import com.intellectual_systems.model.Player;
import com.intellectual_systems.model.Turn;

/**
 *
 * @author Jonathan
 */
public class TurnManager {
    private int currentPlayerIndex;
    private final List<Player> players;
    private Turn currentTurn;

    public TurnManager(List<Player> players) {
        this.players = new ArrayList<>(players);
        this.currentPlayerIndex = 0;
        this.currentTurn = new Turn(players.get(currentPlayerIndex));
    }

    public Turn getCurrentTurn() {
        return currentTurn;
    }
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void setCurrentTurn(Turn turn) {
        this.currentTurn = turn;
    }

    
    public void nextTurn() {
        this.currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        this.currentTurn = new Turn(players.get(currentPlayerIndex));
    }

   
}