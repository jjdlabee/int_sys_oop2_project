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
public class GameReportCommand implements Command {
    private final String format;
    private final GameEngine gameEngine;

    public GameReportCommand(GameEngine gameEngine, String format) {
        this.gameEngine = gameEngine;
        this.format = format;
    }

    @Override
    public void execute() {
        // Implementation for generating game report in the specified format
        System.out.println("Generating game report in " + format + " format...");
        // Add logic to generate and save the report

        gameEngine.addSystemGameEvent("Generate Report");
    }

}
