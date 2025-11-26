/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.model;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public class GameBoard {
    private final String[][] board;
    private final HashMap<String, Integer> indexes;
    private final HashMap<Integer, String> questions;
    private final int rows;
    private final int cols;

    public GameBoard(List<Category> categories) {
        this.rows = categories.get(0).getQuestions().size() + 1; // +1 for category names
        this.cols = categories.size();
        board = new String[rows][cols];
        indexes = new HashMap<>();
        questions = new HashMap<>();
        for (int i = 0; i < categories.size(); i++) {
            indexes.put(categories.get(i).getName(), i);
        }
        for (int i = 0; i < categories.get(0).getQuestions().size(); i++) {
            indexes.put(categories.get(0).getQuestions().get(i).getValue() + "", i);
            questions.put(categories.get(0).getQuestions().get(i).getValue(), categories.get(0).getQuestions().get(i).getQuestionText() + "");
        }
    }

    public void initializeBoard(List<Category> categories) {
        for (int i = 0; i < rows; i++) {
            if(i != 0){
                for (int j = 0; j < cols; j++) {
                    board[i][j] = "";
                }
            }
            else{
                for (int j = 0; j < cols; j++) {
                    board[i][j] = categories.get(j).getName();
                }
            }
        }
    }

    public void setCell(String category, int value, Question question) {
        Integer col = indexes.get(category);
        Integer row = indexes.get(value + "");
        if (col != null && row != null && row >= 0 && row < rows) {
            board[row + 1][col] = question.getValue() + "";
        } else {
            throw new IndexOutOfBoundsException("Invalid category or value");
        }
    }

    public String renderQuestionValue(String category, int value) {
        StringBuilder sb = new StringBuilder();
        Integer col = indexes.get(category);
        Integer row = indexes.get(value + "");
        String questionText = questions.get(value);
        if (col != null && row != null && row >= 0 && row < rows) {
            sb.append("Value: ").append(value).append(", Question: ").append(questionText).append("\n");
            return sb.toString();
        } else {
            return "Invalid category or value";
        }
    }

    public void loadQuestions(List<Category> categories) {
        for (int j = 0; j < categories.size(); j++) {
            Category category = categories.get(j);
            for (int i = 0; i < category.getQuestions().size(); i++) {
                Question question = category.getQuestions().get(i);
                setCell(category.getName(), question.getValue(), question);
            }
        }
    }

    public void clearCell(String category, int value) {
        Integer col = indexes.get(category);
        Integer row = indexes.get(value + "");
        if (col != null && row != null && row >= 0 && row < rows) {
            board[row + 1][col] = " ";
        } else {
            throw new IndexOutOfBoundsException("Invalid category or value");
        }
    }   

    public String renderCategory(String category) {
        StringBuilder sb = new StringBuilder();
        Integer col = indexes.get(category);
        if (col == null) {
            throw new IndexOutOfBoundsException("Invalid category");
        }
        
        sb.append("Category:").append(category).append("\n");
        sb.append("---------------------\n");
        for (int i = 1; i < rows; i++) {
            String cell = board[i][col] != null ? board[i][col] : " ";
            int width = 15;
            int pad = Math.max(0, width - cell.length());
            int left = pad / 2;
            int right = pad - left;
            sb.append(" ".repeat(left)).append(cell).append(" ".repeat(right)).append("\n");
        }
        sb.append("---------------------\n");
        
        return sb.toString();
    }

    public String renderEntireGameBoard() {
        StringBuilder sb = new StringBuilder();
        
    // Header will be generated dynamically once we know the total table width

        // Calculate column widths based on content
        int[] colWidths = new int[cols];
        for (int j = 0; j < cols; j++) {
            colWidths[j] = 15; // minimum width
            for (int i = 0; i < rows; i++) {
                String cell = board[i][j] != null ? board[i][j] : " ";
                colWidths[j] = Math.max(colWidths[j], cell.length() + 2);
            }
        }
        
        // Build horizontal separator
        StringBuilder separator = new StringBuilder();
        for (int j = 0; j < cols; j++) {
            separator.append("-".repeat(colWidths[j]));
            if (j < cols - 1) {
                separator.append("+");
            }
        }
        String horSeparator = separator.toString();
        
                // Build and append a dynamic header line centered on the table width
                int totalWidth = horSeparator.length();
                String title = " Current Game Board ";
                int leftPad = Math.max(0, (totalWidth - title.length()) / 2);
                int rightPad = Math.max(0, totalWidth - title.length() - leftPad);
                sb.append("\n")
                    .append("-".repeat(leftPad))
                    .append(title)
                    .append("-".repeat(rightPad))
                    .append("\n");

                // Add top separator
                sb.append(horSeparator).append("\n");
        
        // Add rows
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String cell = board[i][j] != null ? board[i][j] : " ";
                int width = colWidths[j];
                int pad = Math.max(0, width - cell.length());
                int left = pad / 2;
                int right = pad - left;
                sb.append(" ".repeat(left)).append(cell).append(" ".repeat(right));
                if (j < cols - 1) {
                    sb.append("|");
                }
            }
            sb.append("\n");
            
            // Add separator after each row (including after last row for clean border)
            sb.append(horSeparator).append("\n");
        }
        
        return sb.toString();
    }
}