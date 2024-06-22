package com.github.arif043.chess.entity;

/**
 * @author Arif Ertugrul
 * @date 22.06.24
 */
public class King extends Figure {

    public King(int xPosition, int yPosition, boolean isBlack) {
        super(xPosition, yPosition, isBlack);
    }

    @Override
    public void validateMoves(Figure[][] figure) {

    }
}