package com.github.arif043.chess.service;

import com.github.arif043.chess.entity.Figure;
import com.github.arif043.chess.entity.Position;

import java.util.ArrayList;

/**
 * @author Arif Ertugrul
 * @date 22.06.24
 */
public class PlayerActionService {

    private RootService rootService;

    public PlayerActionService(RootService rootService) {
        this.rootService = rootService;
    }

    public ArrayList<Position> showMoveOptions(int x, int y) {
        var game = rootService.getCurrentGame();
        var currentFigure = game.getBoard()[y][x];
        System.out.println(currentFigure.getClass().getSimpleName());
        return currentFigure.validateMoves(game.getBoard());
    }

    public void moveFigure(Figure figure) {

    }
}