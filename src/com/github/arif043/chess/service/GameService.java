package com.github.arif043.chess.service;

import com.github.arif043.chess.entity.*;

import javax.swing.*;

/**
 * @author Arif Ertugrul
 * @date 22.06.24
 */
public class GameService {

    private RootService rootService;

    public GameService(RootService rootService) {
        this.rootService = rootService;
    }

    public void startNewGame(String player1Name, String player2Name) {
        rootService.setCurrentGame(new Game());
        rootService.getCurrentGame().setFirstPlayer(new Player(player1Name, false));
        rootService.getCurrentGame().setSecondPlayer(new Player(player2Name, true));
        rootService.getCurrentGame().setCurrentPlayer(new Player(player1Name, true));
        preparePlayersFigure(false);
        preparePlayersFigure(true);
        rootService.getCurrentGame().setCurrentPlayer(rootService.getCurrentGame().getFirstPlayer());
    }

    private void preparePlayersFigure(boolean isBlack) {
        var y = isBlack ? 0 : 7;
        var board = rootService.getCurrentGame().getBoard();
        for (int k = 0; k < 8; k++) {
            board[isBlack ? 1 : 6][k] = new Pawn(k, isBlack ? 1 : 6, isBlack);
        }
        board[y][0] = new Rook(0, y, isBlack);
        board[y][1] = new Knight(1, y, isBlack);
        board[y][2] = new Bishop(2, y, isBlack);
        board[y][3] = new Queen(3, y, isBlack);
        board[y][4] = new King(4, y, isBlack);
        board[y][5] = new Bishop(5, y, isBlack);
        board[y][6] = new Knight(6, y, isBlack);
        board[y][7] = new Rook(7, y, isBlack);
    }

    public void endGame() {

    }
}