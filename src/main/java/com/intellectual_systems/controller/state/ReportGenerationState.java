/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.controller.state;

import java.util.Scanner;

import com.intellectual_systems.command.GameReportCommand;
import com.intellectual_systems.controller.GameEngine;
import com.intellectual_systems.controller.GameState;

/**
 *
 * @author Jonathan
 */
public class ReportGenerationState implements GameState {
    private static final Scanner scanner = new Scanner(System.in);
    private final GameEngine gameEngine;

    public ReportGenerationState(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void renderCurrentState() {
        System.out.println("\nFinal Scores:");
        gameEngine.getPlayers().forEach(player -> {
            System.out.println(player.getUsername() + ": " + player.getScore() + " points");
        });
        
        System.out.println("\nHow would you like Game Report? (TXT/PDF/DOCX)");
        String choice = scanner.next();
        scanner.nextLine();

        System.out.println("\nGenerating game report in " + choice + " format...");
        GameReportCommand gameReportCommand = new GameReportCommand(gameEngine, choice);
        gameReportCommand.execute();
    }

    @Override
    public void renderNextState() {
        gameEngine.setState(new GameOverState(gameEngine));
        gameEngine.renderCurrentState();
    }
}
