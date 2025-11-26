/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.controller.state;

import java.util.Scanner;

import com.intellectual_systems.controller.GameEngine;
import com.intellectual_systems.controller.GameState;

/**
 *
 * @author Jonathan
 */
public class QuestionSelectState implements GameState {
    private static final Scanner scanner = new Scanner(System.in);
    private final GameEngine gameEngine;

    public QuestionSelectState(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    

    @Override
    public void renderCurrentState() {
        // Implement the logic to render the question selection state
        System.out.println("Question selection state rendering...");
        
        
    }


    @Override
    public void renderNextState() {
        
    }
} 