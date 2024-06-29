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
        return currentFigure.validateMoves(game.getBoard());
    }

    public void moveFigure(int oldX, int oldY, int newX, int newY) {
        System.out.println("move");
        var board = rootService.getCurrentGame().getBoard();
        if (board[oldY][oldX] == null) {
            System.err.println("Moving figure doesnt exist");
            return;
        }
        board[newY][newX] = board[oldY][oldX];
        board[newY][newX].setxPosition(newX);
        board[newY][newX].setyPosition(newY);
        board[oldY][oldX] = null;
    }
}