package com.github.arif043.chess.service;

import com.github.arif043.chess.entity.Figure;
import com.github.arif043.chess.entity.King;
import com.github.arif043.chess.entity.Position;
import com.github.arif043.chess.entity.Rook;

import java.awt.*;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Arif Ertugrul
 * @date 22.06.24
 */
public class PlayerActionService {

    private RootService rootService;
    private Position[] positionsAfterCastling = new Position[2];

    public PlayerActionService(RootService rootService) {
        this.rootService = rootService;
    }

    public ArrayList<Position> showMoveOptions(int x, int y) {
        var game = rootService.getCurrentGame();
        var currentFigure = game.getBoard()[y][x];
        checkCastling();
        return currentFigure.validateMoves(game.getBoard());
    }

    public void moveFigure(int oldX, int oldY, int newX, int newY) {
        var board = rootService.getCurrentGame().getBoard();
        if (board[oldY][oldX] == null) {
            System.err.println("Moving figure doesnt exist");
            return;
        }
        if (board[newY][newX] != null && board[oldY][oldX].isBlack() == board[newY][newX].isBlack()) {
            var king = board[oldY][oldX];
            var rook = board[newY][newX];
            // queenside castling
            int kingStepX = board[newY][newX].getxPosition() == 0 ? -2 : 2;
            int rookStepX = board[newY][newX].getxPosition() == 0 ? 3 : -2;

            board[oldY][oldX] = board[newY][newX] = null;
            king.setxPosition(king.getxPosition() + kingStepX);
            rook.setxPosition(rook.getxPosition() + rookStepX);
            board[oldY][king.getxPosition()] = king;
            board[oldY][rook.getxPosition()] = rook;
            positionsAfterCastling[0] = king.getPosition();
            positionsAfterCastling[1] = rook.getPosition();
            return;
        }

        board[newY][newX] = board[oldY][oldX];
        board[newY][newX].setxPosition(newX);
        board[newY][newX].setyPosition(newY);
        board[oldY][oldX] = null;
    }

    public boolean checkCastling() {
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
        var king = (King) board[kingPos.yCord()][kingPos.xCord()];
        var rook1 = rook1Pos != null ? (Rook) board[rook1Pos.yCord()][rook1Pos.xCord()] : null;
        var rook2 = rook2Pos != null ? (Rook) board[rook2Pos.yCord()][rook2Pos.xCord()] : null;

        king.setKingsideCastleAble(true);
        king.setQueensideCastleAble(true);

        if (rook1 == null || king.isMoved() || rook1.isMoved() && (rook2Pos == null || rook2.isMoved())) {
            king.setKingsideCastleAble(false);
            king.setQueensideCastleAble(false);
            return false;
        }

        if (rook2 != null) {
            checkCastling2(board, rook1, king, 1, 4);
            checkCastling2(board, rook1, king, 5, 7);
        }
        else if (rook1.getxPosition() == 0)
            checkCastling2(board, rook1, king, 1, 4);
        else checkCastling2(board, rook1, king, 5, 7);

        return king.isQueensideCastleAble() || king.isKingsideCastleAble();
    }

    public Position[] getPositionAfterCastling() {
        return positionsAfterCastling;
    }

    private void checkCastling2(Figure[][] board, Rook rook1, King king, int startX, int endX) {
        for (int x = startX; x < endX; x++)
            if (board[rook1.getyPosition()][x] != null)
                if (startX == 1)
                    king.setQueensideCastleAble(false);
                else
                    king.setKingsideCastleAble(false);
    }
}