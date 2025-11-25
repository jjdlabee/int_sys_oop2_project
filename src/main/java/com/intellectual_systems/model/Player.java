/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.model;

/**
 *
 * @author Jonathan
 */
public class Player {
    private final String username;
    private int score = 0;

    public Player(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
    
    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}