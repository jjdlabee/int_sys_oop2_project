/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Vinayak
 */
public class CSVLogger {
    private final String filePath;

    public CSVLogger(String filePath) {
        this.filePath = filePath;
    }

    public void logGameEvents(List<GameEvent> events) {
        File file = new File(this.filePath);
        boolean fileExistsAndNotEmpty = file.exists() && file.length() > 0;

        try (FileWriter writer = new FileWriter(this.filePath)) {
            // Write CSV header only if file is new/empty
            if (!fileExistsAndNotEmpty) {
                writer.append("Case_ID,Player_ID,Activity,Timestamp,Category,Question_Value,Answer_Given,Result\n");
            }

            // Write each event (append)
            for (GameEvent event : events) {
                writer.append(event.getCaseID()).append(",");
                if (event.getTurn() != null && event.getTurn().getPlayer() != null) {
                    writer.append(event.getTurn().getPlayer().getUsername()).append(",");
                } else {
                    writer.append("System,");
                }
                writer.append(event.getActivity()).append(",");
                writer.append(event.getTimestamp()).append(",");
                if (event.getTurn() != null) {
                    writer.append(event.getTurn().getCurrentCategory()).append(",");
                    writer.append(Integer.toString(event.getTurn().getCurrentQuestionValue())).append(",");
                    writer.append(event.getTurn().getCurrentAnswer() != null ? event.getTurn().getCurrentAnswer() : "N/A").append(",");
                    if(event.getTurn().getIsCorrect() != null && event.getTurn().getIsCorrect().equals("Correct")){
                        writer.append("Correct").append(",");
                    } else {
                        writer.append("N/A").append(",");
                    }
                    writer.append(Integer.toString(event.getTurn().getScoreAfterTurn())).append(",");
                } else {
                    // write placeholders for the expected columns
                    writer.append("N/A,N/A,N/A,N/A,N/A");
                }
                writer.append("\n");
            }

        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }
}
