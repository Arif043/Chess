package com.github.arif043.chess.entity;

/**
 * @author Arif Ertugrul
 * @date 22.06.24
 */
public class Game {

    private Player currentPlayer;
    private Player firstPlayer, secondPlayer;
    private Figure[][] board;
    private long firstPlayerRemainingTime;
    private long secondPlayerRemainingTime;
}