/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package com.intellectual_systems.app;

import com.intellectual_systems.controller.GameEngine;
import com.intellectual_systems.controller.GameState;
import com.intellectual_systems.controller.state.StartState;
import com.intellectual_systems.logging.EventLogger;

/**
 *
 * @author Jonathan
 */
public class Main {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        

        GameState initialState = new StartState();

        GameEngine gameEngine = new GameEngine(initialState);
        ((StartState) initialState).setGameEngine(gameEngine);

        EventLogger listener = new EventLogger();
        gameEngine.getGameEvent().addListener(listener);
        gameEngine.renderCurrentState();
    }
}
