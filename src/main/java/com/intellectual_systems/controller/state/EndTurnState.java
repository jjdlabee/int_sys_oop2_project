/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.controller.state;

import java.util.Scanner;

import com.intellectual_systems.command.EndTurnCommand;
import com.intellectual_systems.controller.GameEngine;
import com.intellectual_systems.controller.GameState;

/**
 *
 * @author Jonathan
 */
public class EndTurnState implements GameState {
    private static final Scanner scanner = new Scanner(System.in);
    private final GameEngine gameEngine;

    public EndTurnState(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void renderCurrentState() {
        System.out.println("\nTurn ended for player: " + gameEngine.getTurnManager().getCurrentTurn().getPlayer().getUsername());
        System.out.println("Answer was: " + gameEngine.getTurnManager().getCurrentTurn().getIsCorrect());
        
        if (gameEngine.getTurnManager().getCurrentTurn().getTurnNumber() >= gameEngine.getTotalTurns()) {
            System.out.println("Maximum number of turns reached.");
            renderNextState();
            return;
        }

        System.out.println("Continue? Y/N");
        String choice = scanner.next();
        scanner.nextLine();

        if (choice.equalsIgnoreCase("Y")){
            System.out.println("Continuing to next turn...");
        } else {
            gameEngine.setState(new ReportGenerationState(this.gameEngine));
            gameEngine.renderCurrentState();
            return;
        }

        EndTurnCommand endTurnCommand = new EndTurnCommand(this.gameEngine);
        endTurnCommand.execute();
    }

    @Override
    public void renderNextState() {
        gameEngine.setState(new CategorySelectState(this.gameEngine));
        gameEngine.renderCurrentState();
    }
}
