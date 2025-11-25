/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.controller;

import java.util.ArrayList;
import java.util.List;

import com.intellectual_systems.model.Player;

/**
 *
 * @author Jonathan
 */
public class TurnManager {
    private int currentPlayerIndex;
    private final List<Player> players;

    public TurnManager(List<Player> players) {
        this.players = new ArrayList<>(players);
        this.currentPlayerIndex = 0;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

   
}