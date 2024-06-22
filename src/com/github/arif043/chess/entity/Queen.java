package com.github.arif043.chess.entity;

/**
 * @author Arif Ertugrul
 * @date 22.06.24
 */
public class Queen extends Figure {

    public Queen(int xPosition, int yPosition, boolean isBlack) {
        super(xPosition, yPosition, isBlack);
    }

    @Override
    public void validateMoves(Figure[][] figure) {

    }
}