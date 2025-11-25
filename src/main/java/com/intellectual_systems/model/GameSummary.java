/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.model;

import java.util.ArrayList;

/**
 *
 * @author Jonathan
 */
public class GameSummary {
    int gameId;
    ArrayList<String> playerNames;
    ArrayList<Integer> playerScores;
    ArrayList<String> turnList;

    public GameSummary(int gameId, ArrayList<String> playerNames, ArrayList<Integer> playerScores, ArrayList<String> turnList) {
        this.gameId = gameId;
        this.playerNames = playerNames;
        this.playerScores = playerScores;
        this.turnList = turnList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Game ID: ").append(gameId).append("\n");
        sb.append("Players: ");
        sb.append(playerNames.get(0));
        for (int i = 1; i < playerNames.size(); i++) {
            sb.append(", ").append(playerNames.get(i));
        }
        sb.append("\n\n Gameplay Summary:\n");
        sb.append("----------------\n\n");
        for (int i = 0; i < turnList.size(); i++) {
            sb.append("Turn ").append(i + 1).append(": ").append(turnList.get(i)).append("\n");
        }

        sb.append("\nFinal Scores:\n\n");
        for (int i = 0; i < playerNames.size(); i++) {
            sb.append(playerNames.get(i)).append(": ").append(playerScores.get(i)).append("\n");
        }
        
        return sb.toString();
    }

}
