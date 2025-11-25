/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.controller;
import java.util.List;

import com.intellectual_systems.model.Player;


/**
 *
 * @author Jonathan
 */
public class GameEngine {
    GameState state;
    private List<Player> players;

    public GameEngine(GameState startState) {
        this.state = startState;
    }

    public void renderCurrentState() {
        state.renderCurrentState();
    }

    public void renderNextState() {
        state.renderNextState();
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
