package com.github.arif043.chess.entity;

/**
 * @author Arif Ertugrul
 * @date 22.06.24
 */
public class Game {

    private Player currentPlayer;
    private Player firstPlayer, secondPlayer;
    private Figure[][] board = new Figure[8][8];
    private long firstPlayerRemainingTime;
    private long secondPlayerRemainingTime;

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public Figure[][] getBoard() {
        return board;
    }

    public void setBoard(Figure[][] board) {
        this.board = board;
    }

    public long getFirstPlayerRemainingTime() {
        return firstPlayerRemainingTime;
    }

    public void setFirstPlayerRemainingTime(long firstPlayerRemainingTime) {
        this.firstPlayerRemainingTime = firstPlayerRemainingTime;
    }

    public long getSecondPlayerRemainingTime() {
        return secondPlayerRemainingTime;
    }

    public void setSecondPlayerRemainingTime(long secondPlayerRemainingTime) {
        this.secondPlayerRemainingTime = secondPlayerRemainingTime;
    }
}