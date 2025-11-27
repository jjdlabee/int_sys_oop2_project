/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.command;

import com.intellectual_systems.controller.GameEngine;
import com.intellectual_systems.reporting.DocxReportGenerator;
import com.intellectual_systems.reporting.PdfReportGenerator;
import com.intellectual_systems.reporting.TxtReportGenerator;


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
        if(format.equalsIgnoreCase("TXT")) {
            TxtReportGenerator txtReportGenerator = new TxtReportGenerator();
            txtReportGenerator.generateReport("game_report.txt", gameEngine.getGameSummary().toString());
        } else if(format.equalsIgnoreCase("DOCX")) {
            DocxReportGenerator docxReportGenerator = new DocxReportGenerator();
            docxReportGenerator.generateReport("game_report.docx", gameEngine.getGameSummary().toString());
        } else if(format.equalsIgnoreCase("PDF")) {
            // PDF report generation can be implemented here
            PdfReportGenerator pdfReportGenerator = new PdfReportGenerator();
            pdfReportGenerator.generateReport("game_report.pdf", gameEngine.getGameSummary().toString());

        } else {
            throw new IllegalArgumentException("Unsupported report format: " + format);
        }
        // Implementation for generating game report in the specified format
        System.out.println(gameEngine.getGameSummary().toString());
        // Add logic to generate and save the report

        gameEngine.addSystemGameEvent("Generate Report");
        gameEngine.renderNextState();
    }

}
