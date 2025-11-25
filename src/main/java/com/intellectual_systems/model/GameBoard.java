/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.model;

/**
 *
 * @author Jonathan
 */
public class GameBoard {
    private final String[][] board;
    private final int rows;
    private final int cols;

    public GameBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        board = new String[rows][cols];
    }
    public void setCell(int row, int col, String value) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            board[row][col] = value;
        } else {
            throw new IndexOutOfBoundsException("Invalid row or column index");
        }
    }

    public String getCell(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            return board[row][col];
        } else {
            throw new IndexOutOfBoundsException("Invalid row or column index");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append(board[i][j] != null ? board[i][j] : " ");
                if (j < cols - 1) {
                    sb.append(" | ");
                }
            }
            sb.append("\n");
            if (i < rows - 1) {
                sb.append("-".repeat(cols * 4 - 3)).append("\n");
            }
        }
        return sb.toString();
    }
}