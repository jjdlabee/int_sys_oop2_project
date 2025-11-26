/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.command;

import com.intellectual_systems.controller.GameEngine;

/**
 *
 * @author Jonathan
 */
public class EndTurnCommand implements Command {
    private final GameEngine gameEngine;

    public EndTurnCommand(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void execute() {
        gameEngine.getTurnManager().nextTurn();
        gameEngine.renderNextState();
    }
}
