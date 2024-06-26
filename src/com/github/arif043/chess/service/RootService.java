package com.github.arif043.chess.service;

import com.github.arif043.chess.entity.Game;

/**
 * @author Arif Ertugrul
 * @date 22.06.24
 */
public class RootService {

    private Game currentGame = new Game();
    private GameService gameService = new GameService(this);
    private PlayerActionService playerActionService = new PlayerActionService(this);

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public GameService getGameService() {
        return gameService;
    }

    public PlayerActionService getPlayerActionService() {
        return playerActionService;
    }
}