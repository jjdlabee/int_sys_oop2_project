/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public class GameSummary {
    private final String gameId;
    private final List<String> playerNames;
    private final List<Integer> playerScores;
    private final List<String> turnList;

    public GameSummary(String gameId, List<Player> players) {
        this.gameId = gameId;
        this.playerNames = new ArrayList<>();
        this.playerScores = new ArrayList<>();
        this.turnList = new ArrayList<>();

        for(Player player : players){
            this.playerNames.add(player.getUsername());
        }
    }

    public void addTurn(Turn turn){
        this.turnList.add(turn.toString());
    }

    public void addScores(ArrayList<Player> players){
        for(Player player : players){
            this.playerScores.add(player.getScore());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Game ID: ").append(gameId).append("\n");
        sb.append("Players: ");
        if(playerNames != null){
            sb.append(playerNames.get(0));

            for (int i = 0; i < playerNames.size(); i++) {
                sb.append(", ").append(playerNames.get(i));
            }
        }
        sb.append("\n\n Gameplay Summary:\n");
        sb.append("----------------\n\n");
        // for (int i = 0; i < turnList.size(); i++) {
        //     sb.append("Turn ").append(i + 1).append(": ").append(turnList.get(i)).append("\n");
        // }

        // sb.append("\nFinal Scores:\n\n");
        // for (int i = 0; i < playerNames.size(); i++) {
        //     sb.append(playerNames.get(i)).append(": ").append(playerScores.get(i)).append("\n");
        // }
        
        return sb.toString();
    }

}
