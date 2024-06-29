package com.github.arif043.chess.service;

import com.github.arif043.chess.entity.Figure;
import com.github.arif043.chess.entity.King;
import com.github.arif043.chess.entity.Position;
import com.github.arif043.chess.entity.Rook;

import java.awt.*;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.image.BufferedImage;
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
        rootService.getCurrentGame().getCurrentPlayer().isBlack()
        return currentFigure.validateMoves(game.getBoard());
    }

    public void moveFigure(int oldX, int oldY, int newX, int newY) {
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

    private void checkCastling() {
        var isBlack = rootService.getCurrentGame().getCurrentPlayer().isBlack();
        var board = rootService.getCurrentGame().getBoard();
        var player = rootService.getCurrentGame().getCurrentPlayer();
        Position kingPos = null, rook1Pos = null, rook2Pos = null;

        for (int y = 0; y < 8; y++)
            for (int x = 0; x < 8; x++) {
                if (board[y][x] != null && (board[y][x].isBlack() == player.isBlack())) {
                    if (board[y][x] instanceof King)
                        kingPos = board[y][x].getPosition();
                    if (board[y][x] instanceof Rook) {
                        if (rook1Pos == null)
                            rook1Pos = board[y][x].getPosition();
                        else
                            rook2Pos = board[y][x].getPosition();
                    }
                }
            }
        if (kingPos == null || rook1Pos == null || ((King) board[kingPos.yCord()][kingPos.xCord()]).isMoved() ||
                ((Rook) board[rook1Pos.yCord()][rook1Pos.xCord()]).isMoved() && rook2Pos != null && ((Rook) board[rook2Pos.yCord()][rook2Pos.xCord()]).isMoved()) {
            if (kingPos != null)
                ((King) board[kingPos.yCord()][kingPos.xCord()]).setCastlingAble(false);
        }
    }
}