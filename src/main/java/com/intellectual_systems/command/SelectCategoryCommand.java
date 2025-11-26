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
public class SelectCategoryCommand implements Command {
    private final GameEngine gameEngine;
    private final int categoryIndex;

    public SelectCategoryCommand(GameEngine gameEngine, int categoryIndex) {
        this.gameEngine = gameEngine;
        this.categoryIndex = categoryIndex;
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    @Override
    public void execute() {
        String categoryName = gameEngine.getCategories().get(categoryIndex).getName();
        gameEngine.getTurnManager().getCurrentTurn().setCurrentCategory(categoryName);
        gameEngine.getGameBoard().renderCategory(categoryName);
        //gameEngine.renderNextState();
    }
}
