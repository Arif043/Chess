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
//        var firstPlayer = new Player(player1Name, )
        rootService.getCurrentGame().setCurrentPlayer(new Player(player1Name, true));
        preparePlayersFigure(false);
        preparePlayersFigure(true);
//        rootService.getCurrentGame().setCurrentPlayer();
    }

    private void preparePlayersFigure(boolean isBlack) {
        var y = isBlack ? 0 : 7;
        var board = rootService.getCurrentGame().getBoard();
        for (int k = 0; k < 8; k++) {
            board[isBlack ? 1 : 6][k] = new Pawn(0, 0, isBlack);
        }
        board[y][0] = new Rook(0, 0, isBlack);
        board[y][1] = new Knight(0, 0, isBlack);
        board[y][2] = new Bishop(0, 0, isBlack);
        board[y][3] = new Queen(0, 0, isBlack);
        board[y][4] = new King(0, 0, isBlack);
        board[y][5] = new Bishop(0, 0, isBlack);
        board[y][6] = new Knight(0, 0, isBlack);
        board[y][7] = new Rook(0, 0, isBlack);
    }

    public void endGame() {

    }
}